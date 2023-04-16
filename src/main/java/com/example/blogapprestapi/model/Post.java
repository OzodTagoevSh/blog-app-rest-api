package com.example.blogapprestapi.model;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(
        name = "posts", uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})}
)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", unique = true)
    private String title;
    private String description;
    private String content;
}
