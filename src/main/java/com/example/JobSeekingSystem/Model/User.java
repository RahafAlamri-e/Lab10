package com.example.JobSeekingSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name must not be empty")
    @Size(min = 5, message = "Name must be more than 4 letters")
    @Pattern(regexp = "^[a-zA-z]+$", message = "Name must be only Letters")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Email(message = "Email must be correct")
    @Column(columnDefinition = "varchar(50) not null unique")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String password;

    @NotNull(message = "Age must not be null")
    @Min(value = 22, message = "Age must be more than 21")
    @Column(columnDefinition = "int not null check(age>21)")
    private int age;

    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "^(JOB_SEEKER|EMPLOYER)$", message = "Role must be either 'JOB_SEEKER' or 'EMPLOYER'")
    @Column(columnDefinition = "varchar(20) not null check(role='JOB_SEEKER' or role='EMPLOYER')")
    private String role;
}
