package org.coffeeprojects.pmda.project;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ProjectMapperImpl.class)
@RunWith(SpringRunner.class)
public class ProjectMapperTest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void to_entity_should_map_project_jira_bean_to_user_entity() {

        Date date = new Date();

        // Given
        ProjectJiraBean projectJiraBean = new ProjectJiraBean()
                .setId("1")
                .setKey("Key")
                .setName("Name");

        // When
        ProjectEntity projectEntity = projectMapper.toEntity(projectJiraBean);

        // Then
        ProjectEntity expectedProjectEntity = new ProjectEntity()
                .setId("1")
                .setKey("Key")
                .setName("Name")
                .setLastCheck(date)
                .setActive(true);
        assertThat(projectEntity.getId()).isEqualTo(expectedProjectEntity.getId());
        assertThat(projectEntity.getKey()).isEqualTo(expectedProjectEntity.getKey());
        assertThat(projectEntity.getName()).isEqualTo(expectedProjectEntity.getName());
    }
}
