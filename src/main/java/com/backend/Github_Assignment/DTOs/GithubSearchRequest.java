package com.backend.Github_Assignment.DTOs;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GithubSearchRequest {
    private String query;
    private String language;
    private String sort;
}
