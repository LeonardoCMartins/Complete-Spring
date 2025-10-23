package com.retorno.review.configs.rabbitmq;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "course")
@Data
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String instructor;

    public Course() {
    }

    public Course(String title, String instructor) {
        this.title = title;
        this.instructor = instructor;
    }

}
