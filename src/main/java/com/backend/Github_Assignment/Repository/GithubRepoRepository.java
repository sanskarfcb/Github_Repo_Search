package com.backend.Github_Assignment.Repository;

import com.backend.Github_Assignment.Model.GithubRepo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GithubRepoRepository extends JpaRepository<GithubRepo, Long> {

    @Query("SELECT r FROM GithubRepo r " +
            "WHERE (:language IS NULL OR r.language = :language) " +
            "AND (:minStars IS NULL OR r.stars >= :minStars)")
    List<GithubRepo> filterRepos(@Param("language") String language,
                                 @Param("minStars") Integer minStars,
                                 Sort sort);
}
