package com.backend.Github_Assignment.Repository;

import com.backend.Github_Assignment.Model.GithubRepo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubRepoRepository extends JpaRepository<GithubRepo,Long> {

}
