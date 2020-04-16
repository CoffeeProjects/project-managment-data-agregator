package org.coffeeprojects.pmda.feature.issue;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.tracker.TrackerTypeEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
public class IssueUtilsTest {

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

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEqualTo(localIssueEntities);
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

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEqualTo(localIssueEntities);
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

        assertThat(IssueUtils.getIssueEntitiesDelta(localIssueEntities, clientIssueEntities)).isEqualTo(localIssueEntities);
    }

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
    public void test_get_unresolved_issue_entities_null() {
        assertThat(IssueUtils.getUnresolvedIssueEntities(null)).isEmpty();
    }

    @Test
    public void test_get_unresolved_issue_entities_with_empty_issue_entities() {
        assertThat(IssueUtils.getUnresolvedIssueEntities(new ArrayList())).isEmpty();
    }

    @Test
    public void test_get_unresolved_issue_entities_with_resolution_date_for_entity() {
        IssueEntity issueEntity = new IssueEntity();
        List<IssueEntity> issueEntities = new ArrayList();
        issueEntities.add(issueEntity);

        assertThat(IssueUtils.getUnresolvedIssueEntities(issueEntities)).isEmpty();
    }

    @Test
    public void test_get_unresolved_issue_entities() {
        IssueEntity issueEntity = new IssueEntity();
        issueEntity.setResolutionDate(Instant.now());
        List<IssueEntity> issueEntities = new ArrayList();
        issueEntities.add(issueEntity);

        assertThat(IssueUtils.getUnresolvedIssueEntities(issueEntities)).isEqualTo(issueEntities);
    }
}
