package com.backend.Github_Assignment.Service;

import com.backend.Github_Assignment.DTOs.GithubRepoResponse;
import com.backend.Github_Assignment.DTOs.GithubSearchRequest;
import com.backend.Github_Assignment.Model.GithubRepo;
import com.backend.Github_Assignment.Repository.GithubRepoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final RestTemplate restTemplate;
    private final GithubRepoRepository githubRepoRepository;

    public List<GithubRepoResponse> searchAndSaveRepo(GithubSearchRequest request) {

        String url = "https://api.github.com/search/repositories?q=" + request.getQuery();

        if (request.getLanguage() != null) {
            url += "+language:" + request.getLanguage();
        }

        url += "&sort=" + (request.getSort() != null ? request.getSort() : "stars");

        ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("GitHub API error");
        }

        List<GithubRepoResponse> resultList = new ArrayList<>();

        for (JsonNode item : response.getBody().get("items")) {
            Long id = item.get("id").asLong();

            GithubRepo repo = githubRepoRepository.findById(id).orElse(new GithubRepo());
            repo.setId(id);
            repo.setName(item.get("name").asText());
            repo.setDescription(item.get("description").asText(null));
            repo.setOwner(item.get("owner").get("login").asText());
            repo.setLanguage(item.get("language").asText(null));
            repo.setStars(item.get("stargazers_count").asInt());
            repo.setForks(item.get("forks_count").asInt());
            repo.setLastUpdated(ZonedDateTime.parse(item.get("updated_at").asText()));

            GithubRepo saved = githubRepoRepository.save(repo);
            resultList.add(GithubRepoResponse.from(saved));
        }

        return resultList;
    }

    public List<GithubRepoResponse> getFilteredRepos(String language, Integer minStars, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.DESC, sortBy != null ? sortBy : "stars");
        return githubRepoRepository.filterRepos(language, minStars, sort)
                .stream()
                .map(GithubRepoResponse::from)
                .collect(Collectors.toList());
    }
}