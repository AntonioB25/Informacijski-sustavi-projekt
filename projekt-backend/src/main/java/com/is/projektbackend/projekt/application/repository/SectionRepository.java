package com.is.projektbackend.projekt.application.repository;

import com.is.projektbackend.projekt.application.model.BookSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<BookSection, Integer> {

    /**
     * Get section by name
     */
    Optional<BookSection> findBySectionNameIgnoreCase(String sectionName);

    BookSection getBySectionNameIgnoreCase (String sectionName);
}
