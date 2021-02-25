package org.coffeeprojects.pmda.feature.project;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ProjectMapperImpl.class, UserMapperImpl.class})
@ExtendWith(SpringExtension.class)
class ProjectMapperTest {

    @Autowired
    private ProjectMapper projectMapper;

    @Test
    void to_entity_should_map_project_jira_bean_to_user_entity() {
        // Given
        ProjectJiraBean projectJiraBean = new ProjectJiraBean()
                .setId("id")
                .setKey("Key")
                .setName("Name")
                .setLead(new UserJiraBean().setDisplayName("Toto"));

        // When
        ProjectEntity projectEntity = projectMapper.toEntity(projectJiraBean);

        // Then
        ProjectEntity expectedProjectEntity = new ProjectEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setKey("Key")
                .setName("Name")
                .setAdministrator(new UserEntity().setId(new CompositeIdBaseEntity()).setDisplayName("Toto"));

        assertThat(projectEntity).isEqualToComparingFieldByField(expectedProjectEntity);
    }

    @Test
    void to_entity_with_project_jira_bean_null() {
        // Given / When / Then
        assertThat(projectMapper.toEntity(null)).isNull();
    }
}
