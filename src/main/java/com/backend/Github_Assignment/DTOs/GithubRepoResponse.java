package com.backend.Github_Assignment.DTOs;

import com.backend.Github_Assignment.Model.GithubRepo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.SplittableRandom;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GithubRepoResponse {
    private Long id;
    private String name;
    private String despription;
    private String owner;
    private String language;

    private int starts;
    private int forks;
    private ZonedDateTime lastUpdated;

    public static GithubRepoResponse from(GithubRepo repo){
        return GithubRepoResponse.builder()
                .id(repo.getId())
                .name(repo.getName())
                .owner(repo.getOwner())
                .language(repo.getLanguage())
                .despription(repo.getDescription())
                .forks(repo.getForks())
                .starts(repo.getStars())
                .lastUpdated(repo.getLastUpdated())
                .build();
    }

}
