package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class IssueUtilsTest {

    @Test
    public void test_get_issue_entities_delta_with_all_issues_are_available() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_client_id_from_issue_entities_null() {
        assertThat(IssueUtils.getKeysFromIssueEntities(null)).isEmpty();
    }

    @Test
    public void test_get_client_id_from_issue_entities_with_empty_list() {
        List<IssueEntity> issueEntities = new ArrayList();
        assertThat(IssueUtils.getKeysFromIssueEntities(issueEntities)).isEmpty();
    }

    @Test
    public void test_get_client_id_from_issue_entities_without_id() {
        IssueEntity issueEntity = new IssueEntity();
        List<IssueEntity> issueEntities = new ArrayList();
        issueEntities.add(issueEntity);
        assertThat(IssueUtils.getKeysFromIssueEntities(issueEntities)).isEmpty();
    }

    @Test
    public void test_get_client_id_from_issue_entities_without_client_id() {
        IssueEntity issueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA));
        List<IssueEntity> issueEntities = new ArrayList();
        issueEntities.add(issueEntity);
        assertThat(IssueUtils.getKeysFromIssueEntities(issueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_null() {
        assertThat(IssueUtils.getIssueEntitiesDelta(null, null)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_with_local_issue_entities_is_null() {
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(null, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_with_client_issue_entities_is_null() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, null)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_with_empty_local_issue_entities_empty() {
        // Local issue entities
        IssueEntity localIssueEntity = new IssueEntity();
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_with_empty_client_issue_entities_empty() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = new IssueEntity();
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_without_client_id_for_local_issue_entities() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_without_id_client_for_client_issue_entities() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_get_issue_entities_delta_with_issue_not_available() {
        // Local issue entities
        IssueEntity localIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity);
        // Client issue entities
        IssueEntity clientIssueEntity = (IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2"));
        List<IssueEntity> clientIssueEntities = new ArrayList();
        clientIssueEntities.add(clientIssueEntity);

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEmpty();
    }

    @Test
    public void test_remove_duplicate_users_no_duplicate_users() {
        UserEntity administrator = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setAdministrator(administrator);

        UserEntity creator1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_1"));
        UserEntity creator2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_2"));
        UserEntity assignee1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_1"));
        UserEntity assignee2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_2"));
        UserEntity reporter1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity reporter2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));

        IssueEntity localIssueEntity1 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setCreator(creator1)
                .setAssignee(assignee1)
                .setReporter(reporter1);
        IssueEntity localIssueEntity2 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2")))
                .setCreator(creator2)
                .setAssignee(assignee2)
                .setReporter(reporter2);
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity1);
        localIssueEntities.add(localIssueEntity2);

        IssueUtils.removeDuplicateUsers(localIssueEntities, projectEntity);
        assertThat(localIssueEntity1.getCreator()).isEqualTo(creator1);
        assertThat(localIssueEntity1.getAssignee()).isEqualTo(assignee1);
        assertThat(localIssueEntity1.getReporter()).isEqualTo(reporter1);
        assertThat(localIssueEntity2.getCreator()).isEqualTo(creator2);
        assertThat(localIssueEntity2.getAssignee()).isEqualTo(assignee2);
        assertThat(localIssueEntity2.getReporter()).isEqualTo(reporter2);
    }

    @Test
    public void test_remove_duplicate_users_between_issues() {
        UserEntity administrator = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setAdministrator(administrator);

        UserEntity creator1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_1"));
        UserEntity creator2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_1"));
        UserEntity assignee1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_1"));
        UserEntity assignee2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_1"));
        UserEntity reporter1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity reporter2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));

        IssueEntity localIssueEntity1 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setCreator(creator1)
                .setAssignee(assignee1)
                .setReporter(reporter1);
        IssueEntity localIssueEntity2 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2")))
                .setCreator(creator2)
                .setAssignee(assignee2)
                .setReporter(reporter2);
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity1);
        localIssueEntities.add(localIssueEntity2);

        IssueUtils.removeDuplicateUsers(localIssueEntities, projectEntity);
        assertThat(localIssueEntity1.getCreator()).isEqualTo(creator1);
        assertThat(localIssueEntity1.getAssignee()).isEqualTo(assignee1);
        assertThat(localIssueEntity1.getReporter()).isEqualTo(reporter1);
        assertThat(localIssueEntity2.getCreator()).isEqualTo(creator2);
        assertThat(localIssueEntity2.getAssignee()).isEqualTo(assignee2);
        assertThat(localIssueEntity2.getReporter()).isEqualTo(reporter2);
    }

    @Test
    public void test_remove_duplicate_users_in_each_issue() {
        UserEntity administrator = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setAdministrator(administrator);

        UserEntity creator1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity creator2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_2"));
        UserEntity assignee1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_1"));
        UserEntity assignee2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));
        UserEntity reporter1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity reporter2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));

        IssueEntity localIssueEntity1 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setCreator(creator1)
                .setAssignee(assignee1)
                .setReporter(reporter1);
        IssueEntity localIssueEntity2 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2")))
                .setCreator(creator2)
                .setAssignee(assignee2)
                .setReporter(reporter2);
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity1);
        localIssueEntities.add(localIssueEntity2);

        IssueUtils.removeDuplicateUsers(localIssueEntities, projectEntity);
        assertThat(localIssueEntity1.getCreator()).isEqualTo(reporter1);
        assertThat(localIssueEntity1.getAssignee()).isEqualTo(assignee1);
        assertThat(localIssueEntity1.getReporter()).isEqualTo(reporter1);
        assertThat(localIssueEntity2.getCreator()).isEqualTo(creator2);
        assertThat(localIssueEntity2.getAssignee()).isEqualTo(reporter2);
        assertThat(localIssueEntity2.getReporter()).isEqualTo(reporter2);
    }

    @Test
    public void test_remove_duplicate_users_in_each_issue_and_administrator() {
        UserEntity administrator = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setAdministrator(administrator);

        UserEntity creator1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity creator2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1_2"));
        UserEntity assignee1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2_1"));
        UserEntity assignee2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));
        UserEntity reporter1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        UserEntity reporter2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));

        IssueEntity localIssueEntity1 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setCreator(creator1)
                .setAssignee(assignee1)
                .setReporter(reporter1);
        IssueEntity localIssueEntity2 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2")))
                .setCreator(creator2)
                .setAssignee(assignee2)
                .setReporter(reporter2);
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity1);
        localIssueEntities.add(localIssueEntity2);

        IssueUtils.removeDuplicateUsers(localIssueEntities, projectEntity);
        assertThat(localIssueEntity1.getCreator()).isEqualTo(reporter1);
        assertThat(localIssueEntity1.getAssignee()).isEqualTo(assignee1);
        assertThat(localIssueEntity1.getReporter()).isEqualTo(administrator);
        assertThat(localIssueEntity2.getCreator()).isEqualTo(creator2);
        assertThat(localIssueEntity2.getAssignee()).isEqualTo(reporter2);
        assertThat(localIssueEntity2.getReporter()).isEqualTo(reporter2);
    }

    @Test
    public void test_remove_duplicate_users_with_some_null() {
        UserEntity administrator = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setAdministrator(administrator);

        UserEntity creator1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_1"));
        UserEntity assignee2 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("3_2"));
        UserEntity reporter1 = (UserEntity) new UserEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1"));

        IssueEntity localIssueEntity1 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("1")))
                .setCreator(creator1)
                .setAssignee(null)
                .setReporter(reporter1);
        IssueEntity localIssueEntity2 = ((IssueEntity) new IssueEntity().setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setClientId("2")))
                .setCreator(null)
                .setAssignee(assignee2)
                .setReporter(null);
        List<IssueEntity> localIssueEntities = new ArrayList();
        localIssueEntities.add(localIssueEntity1);
        localIssueEntities.add(localIssueEntity2);

        IssueUtils.removeDuplicateUsers(localIssueEntities, projectEntity);
        assertThat(localIssueEntity1.getCreator()).isEqualTo(reporter1);
        assertThat(localIssueEntity1.getAssignee()).isNull();
        assertThat(localIssueEntity1.getReporter()).isEqualTo(administrator);
        assertThat(localIssueEntity2.getCreator()).isNull();
        assertThat(localIssueEntity2.getAssignee()).isEqualTo(assignee2);
        assertThat(localIssueEntity2.getReporter()).isNull();
    }
}
