package org.coffeeprojects.pmda.tracker;

import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.tracker.jira.JiraClient;
import org.coffeeprojects.pmda.tracker.mantis.MantisClient;
import org.coffeeprojects.pmda.tracker.redmine.RedmineClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Import(FeignClientsConfiguration.class)
public class TrackerRouter {

    private static final Logger log = LoggerFactory.getLogger(TrackerRouter.class);

    private List<Tracker> trackers = new ArrayList();

    @Autowired
    public TrackerRouter(Decoder decoder, Encoder encoder, Client client, TrackerService trackerService) {
        trackerService.getTrackers().forEach(p -> {
            Tracker tracker = new Tracker();
            tracker.setType(TrackerTypeEnum.valueOf(p.getType().toUpperCase()));
            tracker.setLocalId(p.getLocalId());
            tracker.setClient(buildClient(decoder, encoder, client, getClientInterface(p), p.getUrl(), p.getUser(), p.getPassword()));
            trackers.add(tracker);
        });
    }

    private Class getClientInterface(TrackerParametersBean trackerParametersBean) {
        switch (TrackerTypeEnum.valueOf(trackerParametersBean.getType().toUpperCase())) {
            case JIRA:
                return JiraClient.class;
            case MANTIS:
                return MantisClient.class;
            case REDMINE:
                return RedmineClient.class;
            default:
                log.error("No interface available for the tracker TYPE : " + trackerParametersBean.getType() + "ID : " + trackerParametersBean.getLocalId());
                return null;
        }
    }

    private Object buildClient(Decoder decoder, Encoder encoder, Client client, Class clientClass,
                                      String url, String user, String password) {
        return Feign.builder().client(client)
                .encoder(encoder)
                .decoder(decoder)
                .requestInterceptor(new BasicAuthRequestInterceptor(user, password))
                .target(clientClass, url);
    }

    public static final Object getTracker(TrackerRouter trackerRouter, ProjectEntity projectEntity) {
        if (trackerRouter != null && trackerRouter.trackers != null &&
                projectEntity != null && projectEntity.getId() != null && projectEntity.getId().getTrackerType() != null) {

            Tracker tracker = trackerRouter.trackers.stream()
                    .filter(t -> t.getType() == projectEntity.getId().getTrackerType())
                    .filter(t -> t.getLocalId() == projectEntity.getId().getTrackerLocalId())
                    .findFirst()
                    .orElse(new Tracker());

            return tracker.getClient();
        }
        return null;
    }

    public List<Tracker> getTrackers() {
        return trackers;
    }

    public TrackerRouter setTrackers(List<Tracker> trackers) {
        this.trackers = trackers;
        return this;
    }
}
