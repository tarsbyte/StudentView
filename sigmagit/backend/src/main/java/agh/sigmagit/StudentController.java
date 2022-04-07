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
                           @RequestParam(value = "first_name") String firstName,
                           @RequestParam(value = "last_name") String lastName) {

        if(code.equals(this.code)){
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentRepository.save(student);
            return "Login successful, " + firstName + " " + lastName;
        }
        return "Wrong code";
    }
}
