package org.coffeeprojects.pmda.issueType;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = IssueTypeMapperImpl.class)
@RunWith(SpringRunner.class)
public class IssueTypeMapperTest {

    @Autowired
    private IssueTypeMapper issueTypeMapper;

    @Test
    public void to_entity_should_map_issuetype_jira_bean_to_user_entity() {

        // Given
        IssueTypeJiraBean issueTypeJiraBean = new IssueTypeJiraBean()
                .setId("1")
                .setName("Name")
                .setDescription("Description");

        // When
        IssueTypeEntity issueTypeEntity = issueTypeMapper.toEntity(issueTypeJiraBean);

        // Then
        IssueTypeEntity expectedIssueTypeEntity = new IssueTypeEntity()
                .setId("1")
                .setName("Name")
                .setDescription("Description");
        assertThat(issueTypeEntity).isEqualToComparingFieldByField(expectedIssueTypeEntity);
    }
}
