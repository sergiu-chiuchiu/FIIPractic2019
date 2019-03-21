package ro.fiipractic.mycinema.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Column(name = "full_name")
    private String fullName;
    @Column(name = "telephone")
    private String phone;
    @Email //validari automate pentru email
    @Column(name = "email")
    private String email;

    public Person(Long id, String fullName, String phone, String email) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
    }

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
