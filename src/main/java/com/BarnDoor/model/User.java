package com.BarnDoor.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user_details")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;


    @Column(nullable = false, unique = true)
    String username;

    @Column(name = "password")
    String password;

    Boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER ,cascade=CascadeType.MERGE)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles()
    {
        return roles;
    }
    public void setRoles(Set<Role> roles)
    {
        this.roles = roles;
    }

    public Boolean isEnabled() {
        return true;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "UserLoginDAO [userid=" + id + ", username=" + username + ", password=" + password + "]";
    }
}
