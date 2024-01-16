package com.example.demo.user;


import com.example.demo.anotation.JsonName;
import jakarta.persistence.*;
import java.util.Collection;

@Table(name = "_user")
@Entity
@JsonName(value = "super_man")
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @JsonName(value = "firstname")
    private String firstname;
    private String lastname;
    private String email;
    private String passwd;
    @Enumerated(EnumType.STRING)
    Role role;

    public User() {
    }

    public User(Integer id, String firstname, String lastname, String email, String passwd, Role role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}