package org.coffeeprojects.pmda.tracker;

import feign.Client;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.coffeeprojects.pmda.feature.project.ProjectEntity;
import org.coffeeprojects.pmda.feature.project.ProjectEnum;
import org.coffeeprojects.pmda.tracker.jira.JiraClient;
import org.coffeeprojects.pmda.tracker.mantis.MantisClient;
import org.coffeeprojects.pmda.tracker.redmine.RedmineClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Import(FeignClientsConfiguration.class)
public class TrackerRouter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final TrackerService trackerService;

    private Map<Map<String, String>, Object> clients = new HashMap();

    @Autowired
    public TrackerRouter(Decoder decoder, Encoder encoder, Client client, TrackerService trackerService) {
        this.trackerService = trackerService;

        for (TrackerBean trackerBean : this.trackerService.getTrackers()) {
            Map<String, String> trackerId = new HashMap();
            trackerId.put(trackerBean.getType(), trackerBean.getId());

            this.clients.put(trackerId, buildClient(decoder, encoder, client, getClientInterface(trackerBean),
                    trackerBean.getUrl(), trackerBean.getUser(), trackerBean.getPassword()));
        }
    }

    private Class getClientInterface(TrackerBean trackerBean) {
        if (ProjectEnum.JIRA.toString().equalsIgnoreCase(trackerBean.getType())) {
            return JiraClient.class;
        } else if (ProjectEnum.MANTIS.toString().equalsIgnoreCase(trackerBean.getType())) {
            return MantisClient.class;
        } else if (ProjectEnum.REDMINE.toString().equalsIgnoreCase(trackerBean.getType())) {
            return RedmineClient.class;
        } else {
            logger.error("No interface available for the tracker TYPE : " + trackerBean.getType() + "ID : " + trackerBean.getId());
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

    public static final Object getClient(TrackerRouter trackerRouter, ProjectEntity projectEntity) {
        if (projectEntity != null && projectEntity.getId() != null && projectEntity.getId().getTrackerType() != null) {
            for (Map.Entry<Map<String, String>, Object> trackerEntry : trackerRouter.getClients().entrySet()) {
                for (Map.Entry<String, String> trackerIdEntry : trackerEntry.getKey().entrySet()) {
                    String trackerType = trackerIdEntry.getKey();
                    String trackerId = trackerIdEntry.getValue();
                    Object client = trackerEntry.getValue();
                    if (trackerType.equalsIgnoreCase(projectEntity.getId().getTrackerType().toString()) &&
                            trackerId.equalsIgnoreCase(projectEntity.getId().getTrackerId())) {
                        return client;
                    }
                }
            }
        }
        return null;
    }

    public Map<Map<String, String>, Object> getClients() {
        return clients;
    }

    public TrackerRouter setClients(Map<Map<String, String>, Object> clients) {
        this.clients = clients;
        return this;
    }
}