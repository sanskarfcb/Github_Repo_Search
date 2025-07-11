package com.backend.Github_Assignment.Controller;

import com.backend.Github_Assignment.DTOs.GithubRepoResponse;
import com.backend.Github_Assignment.DTOs.GithubSearchRequest;
import com.backend.Github_Assignment.Service.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubController {


    private final GithubService service;

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> search(@RequestBody GithubSearchRequest request) {
        List<GithubRepoResponse> repos = service.searchAndSaveRepo(request);
        return ResponseEntity.ok(Map.of(
                "message", "Repositories fetched and saved successfully",
                "repositories", repos
        ));
    }

    @GetMapping("/repositories")
    public ResponseEntity<Map<String, Object>> getRepos(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(required = false, defaultValue = "stars") String sort
    ) {
        List<GithubRepoResponse> repos = service.getFilteredRepos(language, minStars, sort);
        return ResponseEntity.ok(Map.of("repositories", repos));
    }
}
