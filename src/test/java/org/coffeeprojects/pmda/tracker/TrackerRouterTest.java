package org.coffeeprojects.pmda.tracker;

import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class TrackerRouterTest {

    @Mock
    private Decoder decoder;

    @Mock
    private Encoder encoder;

    @Mock
    private Client client;

    @Mock
    private TrackersProperties trackersProperties;

    private TrackerRouter trackerRouter;

    @BeforeEach
    public void setup() {
        trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
    }

    @Test
    public void test_get_client_with_null_parameters() {
        assertThat(trackerRouter.getTracker(null)).isNull();
    }

    @Test
    public void test_get_client_with_projectentity_null() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerType.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties).setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(null)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_null() {
        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerType.JIRA).setLocalId("1").setClientId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(trackerRouter.getTracker(projectEntity)).isNotNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_trackerId_not_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerType.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("2").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_trackertype_not_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerType.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("2").setTrackerType(TrackerType.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_null() {
        assertThat(trackerRouter.getTracker(null)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_null() {
        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerType.JIRA).setLocalId("1").setClientId("1");

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(null)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_client_id_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("2"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerType.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_local_id_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("2").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerType.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_type_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerType.MANTIS).setTrackerLocalId("1").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerType.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_trackers_and_project_entity_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerType.JIRA).setTrackerLocalId("1").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerType.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, trackersProperties);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(trackerRouter.getTracker(projectEntity)).isNotNull();
    }
}
