package com.backend.Github_Assignment.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class GithubRepo {
    @Id
    private Long id;
    private String despricption;
    private String owner;
    private String language;
    private int starts;
    private int forks;

    private ZonedDateTime lastUpdated;

}
