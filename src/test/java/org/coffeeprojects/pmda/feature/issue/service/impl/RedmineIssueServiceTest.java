package org.coffeeprojects.pmda.feature.issue.service.impl;

import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class RedmineIssueServiceTest {

    private RedmineIssueService redmineIssueService;

    @BeforeEach
    void setUp() {
        redmineIssueService = new RedmineIssueService();
    }

    @Test
    void update_last_modified_issues_should_update_issues() {
        assertThrows(UnsupportedOperationException.class, () -> redmineIssueService.updateLastModifiedIssues(new ProjectEntity()));
    }

    @Test
    void delete_missing_issues_should_delete_issues() {
        assertThrows(UnsupportedOperationException.class, () -> redmineIssueService.deleteMissingIssues(new ProjectEntity()));
    }
}
