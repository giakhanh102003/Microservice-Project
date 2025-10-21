package org.example.authservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @NotNull
    @Column(name = "FULLNAME", nullable = false)
    private String fullName;
    @NotNull
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @NotNull
    @Column(name = "PASSWORD_HASH", nullable = false)
    private String password;
    @Column(name = "CREATED_AT", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date createdAt;
    @Column (name = "ROLE", nullable = false)
    private String role;
}

