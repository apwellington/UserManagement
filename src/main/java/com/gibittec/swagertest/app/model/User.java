package com.gibittec.swagertest.app.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data // genera set y get
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "rol")
    private Integer rol;

    @Column(name = "active")
    private Boolean activ;

}
