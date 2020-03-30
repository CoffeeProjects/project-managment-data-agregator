package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.issue.jirabean.FieldsJiraBean;
import org.coffeeprojects.pmda.issue.jirabean.IssueJiraBean;
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
                                .setCreator(new UserJiraBean().setDisplayName("Bruce Wayne"))
                );
        // TODO: completer le bean avec tous les champs

        // When
        IssueEntity issueEntity = issueMapper.toEntity(issueJiraBean);

        // Then
        IssueEntity expectedIssueEntity = new IssueEntity()
                .setId("id1")
                .setKey("key1")
                .setCreator(new UserEntity().setDisplayName("Bruce Wayne"));

        // TODO: equals qui ne marche pas alors que lorsque qu on regarde dans la console ils sont egaux ?
        assertThat(issueEntity).isEqualToComparingFieldByField(expectedIssueEntity);
    }
}
