package com.backend.Github_Assignment.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GithubRepo {
    @Id
    private Long id;
    private String name;
    @Column(length = 1000)
    private String description;
    private String owner;
    private String language;
    private int stars;
    private int forks;

    private ZonedDateTime lastUpdated;

}
