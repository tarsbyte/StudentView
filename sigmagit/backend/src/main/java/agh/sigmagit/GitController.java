package agh.sigmagit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

public class GitController {
    private static String adminName = "";
    private static String adminToken = "";
    private static final String jsonName = "meta_data.json";

    public GitController(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(jsonName))
        {
            Object meta_data = jsonParser.parse(reader);
            JSONObject jsonObject = (JSONObject) meta_data;
            adminName = (String) jsonObject.get("adminName");
            adminToken = (String) jsonObject.get("adminToken");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    private static int execute(String Type, URL url, String data) throws IOException {
        String encoded_meta_data = Base64.getUrlEncoder().encodeToString((adminName + ":" + adminToken).getBytes(StandardCharsets.UTF_8));

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod(Type);
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/vnd.github.v3+json");
        http.setRequestProperty("Authorization", "Basic " + encoded_meta_data);

        http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        byte[] out = data.getBytes(StandardCharsets.UTF_8);
        OutputStream stream = http.getOutputStream();
        stream.write(out);

        http.disconnect();
        return http.getResponseCode();
    }

    private static String getContent(String Type, URL url, String type) throws IOException {
        String encoded_meta_data = Base64.getUrlEncoder().encodeToString((adminName + ":" + adminToken).getBytes(StandardCharsets.UTF_8));

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod(Type);
        http.setDoOutput(true);
        http.setRequestProperty("Accept", "application/vnd.github.v3+json");
        http.setRequestProperty("Authorization", "Basic " + encoded_meta_data);

        BufferedReader br = null;

        String contentURL = "";

        if (http.getResponseCode() > 199 || http.getResponseCode() < 210 ) {
            br = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                if(type.equals("rawURL")){
                    if(strCurrentLine.contains("download_url")) { // get the raw github link
                        int startPos = strCurrentLine.indexOf("download_url");
                        int endPos = strCurrentLine.indexOf("type");
                        contentURL = strCurrentLine.substring(startPos+15, endPos-3);
                    }
                }
                else if(type.equals("rawContent")){
                    if(strCurrentLine.contains("content")) { // get the content of file
                        int startPos = strCurrentLine.indexOf("\"content\":");
                        int endPos = strCurrentLine.indexOf("\"encoding\":");
                        contentURL = strCurrentLine.substring(startPos+11, endPos-2);
                    }
                }
                else if(type.equals("gitHubLink")){
                    if(strCurrentLine.contains("html_url")) { // get the content of file
                        int startPos = strCurrentLine.indexOf("\"html_url\":");
                        int endPos = strCurrentLine.indexOf("\"git_url\":");
                        contentURL = strCurrentLine.substring(startPos+12, endPos-2);
                    }
                }
                else if(type.equals("SHA")){
                    if(strCurrentLine.contains("sha")) { // get the content of file
                        int startPos = strCurrentLine.indexOf("\"sha\":");
                        int endPos = strCurrentLine.indexOf("\"size\":");
                        contentURL = strCurrentLine.substring(startPos+7, endPos-2);
                    }
                }
            }
        }
        http.disconnect();
        return contentURL;
    }

    public static boolean createRepository(String repositoryName) throws IOException {
        URL url = new URL("https://api.github.com/user/repos");

        JSONObject data = new JSONObject();
        data.put("auto_init", true);
        data.put("private", false);
        data.put("name", repositoryName);
        data.put("gitignore_template", "nanoc");

        int code = execute("POST", url, String.valueOf(data));
        return code == 201 || code == 422; // if repository was created successfully
    }

    public static boolean addCollaborator(String repositoryName, String studentName) throws IOException{

        String urlString = "https://api.github.com/repos/" + adminName + "/" + repositoryName + "/collaborators/" + studentName;
        URL url = new URL(urlString);

        JSONObject data = new JSONObject();
        data.put("permission", "push");

        int code = execute("PUT", url, String.valueOf(data));
        return code == 201 || code == 422;
    }

    public static String getFileContent(String repositoryName, String pathToFile) throws IOException {
        String urlString = "https://api.github.com/repos/" + adminName + "/" + repositoryName + "/contents/" + pathToFile;
        URL url = new URL(urlString);

        //String code = getContent("GET", url, "rawURL");
        //String code = getContent("GET", url, "rawContent");
        String code = getContent("GET", url, "gitHubLink"); // - works only if repository is public
        return code;
    }

    private static String encodeFile(File file){
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            return Base64.getEncoder().encodeToString(fileContent);
        } catch (IOException e) {
            throw new IllegalStateException("could not read file " + file, e);
        }
    }

    public static boolean addFile(String repositoryName, String pathToFile) throws IOException {

        File file = new File(pathToFile);
        String content = encodeFile(file);
        String urlString = "https://api.github.com/repos/" + adminName + "/" + repositoryName + "/contents/" + pathToFile;
        URL url = new URL(urlString);

        JSONObject data = new JSONObject();
        data.put("message", "my commit file");
        data.put("content", content);

        int code = execute("PUT", url, String.valueOf(data));
        return code == 201;
    }

    public static boolean updateFile(String repositoryName, String pathToFile) throws IOException {

        String sha = "";
        String urlString = "https://api.github.com/repos/" + adminName + "/" + repositoryName + "/contents/" + pathToFile;
        URL url = new URL(urlString);

        // get sha of file
        sha = getContent("GET", url, "SHA");

        // update file
        File file = new File(pathToFile);
        String content = encodeFile(file);

        JSONObject data = new JSONObject();
        data.put("message", "my commit file");
        data.put("content", content);
        data.put("sha", sha);

        int code = execute("PUT", url, String.valueOf(data));
        return code == 201 || code == 200;
    }

    public static boolean deleteFile(String repositoryName, String pathToFile) throws IOException {

        String sha = "";
        String urlString = "https://api.github.com/repos/" + adminName + "/" + repositoryName + "/contents/" + pathToFile;
        URL url = new URL(urlString);

        // get sha of file
        sha = getContent("GET", url, "SHA");

        // delete file
        JSONObject data = new JSONObject();
        data.put("message", "my commit file");
        data.put("sha", sha);

        int code = execute("DELETE", url, String.valueOf(data));
        return code == 201 || code == 200;
    }

}