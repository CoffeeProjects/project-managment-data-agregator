package org.coffeeprojects.pmda.feature.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StatusMapperImpl.class)
@ExtendWith(SpringExtension.class)
public class StatusMapperTest {

    @Autowired
    private StatusMapper statusMapper;

    @Test
    public void to_entity_should_map_status_jira_bean_to_user_entity() {
        // Given
        StatusJiraBean statusJiraBean = new StatusJiraBean()
                .setName("KO")
                .setDescription("Nom KO");

        // When
        StatusEntity statusEntity = statusMapper.toEntity(statusJiraBean);

        // Then
        StatusEntity expectedStatusEntity = new StatusEntity()
                .setName("KO")
                .setDescription("Nom KO");

        assertThat(statusEntity.getName()).isEqualTo(expectedStatusEntity.getName());
        assertThat(statusEntity.getDescription()).isEqualTo(expectedStatusEntity.getDescription());
    }
}
