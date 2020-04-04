package org.coffeeprojects.pmda.status;

import org.coffeeprojects.pmda.version.VersionEntity;
import org.coffeeprojects.pmda.version.VersionJiraBean;
import org.coffeeprojects.pmda.version.VersionMapper;
import org.coffeeprojects.pmda.version.VersionMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StatusMapperImpl.class)
@RunWith(SpringRunner.class)
public class StatusMapperTest {

    @Autowired
    private StatusMapper statusMapper;

    @Test
    public void to_entity_should_map_status_jira_bean_to_user_entity() {
        // Given
        StatusJiraBean statusJiraBean = new StatusJiraBean()
                .setId("1")
                .setName("KO")
                .setDescription("Nom KO");

        // When
        StatusEntity statusEntity = statusMapper.toEntity(statusJiraBean);

        // Then
        StatusEntity expectedStatusEntity = ((StatusEntity) new StatusEntity()
                .setId("1"))
                .setName("KO")
                .setDescription("Nom KO");
        assertThat(statusEntity).isEqualToComparingFieldByField(expectedStatusEntity);
    }
}
