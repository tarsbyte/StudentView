package agh.sigmagit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Controller
@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // Static for now
    String code = "ABCDEF";

    @GetMapping("/student")
    public String studentCode() {
        return code;
    }

    @GetMapping("/student_login")
    public String studentLogin(@RequestParam(value = "code") String code,
                               @RequestParam(value = "index") Long index,
                               @RequestParam(value = "name") String name) {

        if(code.equals(this.code)){
            Student student = new Student();
            student.setId(index);
            student.setName(name);
            studentRepository.save(student);

            String repositoryName = "test_lab_" + student.getId();
            if(GitController.createRepository(repositoryName))
                return "Login successful, repository name: " + repositoryName;
            else
                return "Error creating repository";
        }
        return "Wrong code";
    }
}
