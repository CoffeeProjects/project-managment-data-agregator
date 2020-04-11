package org.coffeeprojects.pmda.tracker;

import feign.Client;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class TrackerRouterTest {

    @Mock
    private Decoder decoder;

    @Mock
    private Encoder encoder;

    @Mock
    private Client client;

    @Mock
    private TrackerService trackerService;

    @Test
    public void test_get_client_with_null_parameters() {
        assertThat(TrackerRouter.getTracker(null, null)).isNull();
    }

    @Test
    public void test_get_client_with_projectentity_null() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerTypeEnum.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService).setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(trackerRouter, null)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_null() {
        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(TrackerRouter.getTracker(null, projectEntity)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("1").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNotNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_trackerId_not_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerTypeEnum.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("2").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNull();
    }

    @Test
    public void test_get_client_with_trackerrouter_and_projectentity_trackertype_not_match() {
        // Tracker
        TrackerParametersBean trackerParametersBean = new TrackerParametersBean().setType(TrackerTypeEnum.JIRA).setLocalId("1").setClient(new Object());
        // Trackers
        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean);
        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService).setTrackerParametersBeans(trackerParametersBeans);

        // ProjectEntity
        CompositeIdBaseEntity projectId = new CompositeIdBaseEntity().setClientId("1").setTrackerLocalId("2").setTrackerType(TrackerTypeEnum.JIRA);
        ProjectEntity projectEntity = ((ProjectEntity) new ProjectEntity().setId(projectId))
                .setKey("PMDA");

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_null() {
        assertThat(TrackerRouter.getTracker(null, null)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_null() {
        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1");

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(null, null)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_client_id_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setTrackerLocalId("1").setClientId("2"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_local_id_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setTrackerLocalId("2").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_project_entity_type_not_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.MANTIS).setTrackerLocalId("1").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNull();
    }

    @Test
    public void test_get_tracker_with_trackers_and_project_entity_match() {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setId(new CompositeIdBaseEntity().setTrackerType(TrackerTypeEnum.JIRA).setTrackerLocalId("1").setClientId("1"));

        TrackerParametersBean trackerParametersBean1 = new TrackerParametersBean();
        trackerParametersBean1.setType(TrackerTypeEnum.JIRA).setLocalId("1").setClientId("1");
        trackerParametersBean1.setClient(new Object());

        List<TrackerParametersBean> trackerParametersBeans = new ArrayList();
        trackerParametersBeans.add(trackerParametersBean1);

        TrackerRouter trackerRouter = new TrackerRouter(decoder, encoder, client, trackerService);
        trackerRouter.setTrackerParametersBeans(trackerParametersBeans);

        assertThat(TrackerRouter.getTracker(trackerRouter, projectEntity)).isNotNull();
    }
}
