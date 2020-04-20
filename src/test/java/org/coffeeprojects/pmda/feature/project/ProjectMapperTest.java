package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.feature.user.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ProjectMapperImpl.class, UserMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class ProjectMapperTest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    public void test_to_entity_should_map_project_jira_bean_to_user_entity() {

        Date date = new Date();

        // Given
        ProjectJiraBean projectJiraBean = new ProjectJiraBean()
                .setKey("Key")
                .setName("Name");

        // When
        ProjectEntity projectEntity = projectMapper.toEntity(projectJiraBean);

        // Then
        ProjectEntity expectedProjectEntity = new ProjectEntity()
                .setKey("Key")
                .setName("Name");

        assertThat(projectEntity.getKey()).isEqualTo(expectedProjectEntity.getKey());
        assertThat(projectEntity.getName()).isEqualTo(expectedProjectEntity.getName());
    }
}
