package com.taka.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Id
    private String email;
    private String password;

    @OneToOne
    private Municipal municipal;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE", joinColumns = {
            @JoinColumn(name = "USER_NAME")},
            inverseJoinColumns = {
                @JoinColumn(name = "ROLE_NAME")
            }
    )

    private Set<Role> role;

//    public String getUsername() {
//        return username;
//    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public Municipal getMunicipal() {
        return municipal;
    }

    public void setMunicipal(Municipal municipal) {
        this.municipal = municipal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
