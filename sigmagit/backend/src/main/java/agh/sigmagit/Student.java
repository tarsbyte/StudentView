package agh.sigmagit;

import javax.persistence.*;

@Entity(name = "Student")
@Table(name = "Students")
public class Student {

    @Id
    @Column(name = "GithubName", nullable = false)
    private String githubName;
    @Column(name = "StudentName", nullable = false)
    private String studentName;
    @Column(name = "RepositoryName", nullable = false)
    private String repositoryName;

    public String getGithubName() {
        return githubName;
    }

    public void setGithubName(String githubName) {
        this.githubName = githubName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

}
