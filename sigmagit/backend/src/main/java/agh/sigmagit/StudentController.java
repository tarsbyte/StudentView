package agh.sigmagit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@Controller
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Static for now
    String code = "ABCDEF";

    @GetMapping("/student_code")
    public String studentCode() {
        return code;
    }

    @GetMapping("/student_login")
    public String studentLogin(@RequestParam(value = "code") String code,
                               @RequestParam(value = "index") Long index,
                               @RequestParam(value = "name") String name) {

        JsonObject response = new JsonObject();

        if(code.equals(this.code)){

            String repositoryName = "test_lab_" + index;

            if(!GitController.createRepository(repositoryName)){
                response.addProperty("responseCode", "REPO_ERROR");
                return response.toString();
            }


            Student student = new Student();
            student.setIndex(index);
            student.setName(name);
            student.setRepositoryName(repositoryName);
            studentRepository.save(student);

            response.addProperty("responseCode", "OK");
            response.addProperty("name", name);
            response.addProperty("repositoryName", repositoryName);
            return response.toString();
        }

        response.addProperty("responseCode", "CODE_ERROR");
        return response.toString();
    }

    @GetMapping("/student_list")
    public String studentList(){
        List<Student> students = studentRepository.findAll();
        return new Gson().toJson(students);
    }
}
