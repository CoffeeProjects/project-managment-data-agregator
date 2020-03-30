package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.status.StatusEntity;
import org.coffeeprojects.pmda.status.StatusJiraBean;
import org.coffeeprojects.pmda.user.UserEntity;
import org.coffeeprojects.pmda.user.UserJiraBean;
import org.coffeeprojects.pmda.user.UserMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {UserMapperImpl.class, IssueMapperImpl.class})
@RunWith(SpringRunner.class)
public class IssueMapperTest {
    @Autowired
    private IssueMapper issueMapper;

    @Test
    public void to_entity_should_map_issue_jira_bean_to_issue_entity() {
        // Given
        IssueJiraBean issueJiraBean = new IssueJiraBean()
                .setId("id1")
                .setKey("key1")
                .setExpand("expand")
                .setFields(
                        new FieldsJiraBean()
                                .setSummary("summary")
                                .setStatus(new StatusJiraBean().setId("11"))
                                .setCreator(new UserJiraBean().setAccountId("11111"))
                );
        // TODO: completer le bean avec tous les champs

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId("id1")
                .setKey("key1")
                .setSummary("summary")
                .setStatus(new StatusEntity().setId("11"))
                .setCreator(new UserEntity().setAccountId("11111"));

        assertThat(issueEntity).isEqualTo(expectedIssueEntity);
    }
}
