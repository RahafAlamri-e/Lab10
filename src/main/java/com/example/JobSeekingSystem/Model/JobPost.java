package com.example.JobSeekingSystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title must not be empty")
    @Size(min = 5, message = "Title must be more than 4 characters")
    @Column(columnDefinition = "varchar(100) not null")
    private String title;

    @NotEmpty(message = "Description must not be empty")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotEmpty(message = "Location must not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @NotNull(message = "Salary must not be null")
    @Positive(message = "Salary must be a positive number")
    @Column(columnDefinition = "double not null check(salary>0)")
    private double salary;


    @CreationTimestamp
    @Column(columnDefinition = "timestamp not null default current_timestamp")
    private Date postedDate;
}
