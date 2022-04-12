package agh.sigmagit;

import javax.persistence.*;

@Entity(name = "Student")
@Table(name = "Students")
public class Student {

    @Id
    @Column(name = "index", nullable = false)
    private Long index;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "repositoryName", nullable = false)
    private String repositoryName;

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

}
