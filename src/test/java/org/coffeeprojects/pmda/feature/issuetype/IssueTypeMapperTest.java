package org.coffeeprojects.pmda.feature.issuetype;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class IssueTypeMapperTest {

    private final IssueTypeMapper issueTypeMapper = Mappers.getMapper(IssueTypeMapper.class);

    @Test
    void to_entity_should_map_issuetype_jira_bean_to_user_entity() {
        // Given
        IssueTypeJiraBean issueTypeJiraBean = new IssueTypeJiraBean()
                .setId("id")
                .setName("Name")
                .setDescription("Description");

        // When
        IssueTypeEntity issueTypeEntity = issueTypeMapper.toEntity(issueTypeJiraBean);

        // Then
        IssueTypeEntity expectedIssueTypeEntity = new IssueTypeEntity()
                .setId(new CompositeIdBaseEntity().setClientId("id"))
                .setName("Name")
                .setDescription("Description");

        assertThat(issueTypeEntity).isEqualToComparingFieldByField(expectedIssueTypeEntity);
    }
}
