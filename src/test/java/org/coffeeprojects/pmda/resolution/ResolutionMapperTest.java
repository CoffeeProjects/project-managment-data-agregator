package org.coffeeprojects.pmda.resolution;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ResolutionMapperImpl.class)
@RunWith(SpringRunner.class)
public class ResolutionMapperTest {

    @Autowired
    private ResolutionMapper resolutionMapper;

    @Test
    public void to_entity_should_map_resolution_jira_bean_to_user_entity() {
        // Given
        ResolutionJiraBean resolutionJiraBean = new ResolutionJiraBean()
                .setId("1")
                .setName("KO")
                .setDescription("Nom KO");

        // When
        ResolutionEntity resolutionEntity = resolutionMapper.toEntity(resolutionJiraBean);

        // Then
        ResolutionEntity expectedResolutionEntity = new ResolutionEntity()
                .setId("1")
                .setName("KO")
                .setDescription("Nom KO");
        assertThat(resolutionEntity).isEqualToComparingFieldByField(expectedResolutionEntity);
    }
}
