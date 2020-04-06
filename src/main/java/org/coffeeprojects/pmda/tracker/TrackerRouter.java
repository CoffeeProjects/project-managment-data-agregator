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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Import(FeignClientsConfiguration.class)
public class TrackerRouter {

    @Autowired
    private final TrackerService trackerService;

    private Map<String, JiraClient> jiraClients = new HashMap();

    private Map<String, MantisClient>  mantisClient = new HashMap();

    private Map<String, RedmineClient> redmineClient = new HashMap();;

    @Autowired
    public TrackerRouter(Decoder decoder, Encoder encoder, Client client, TrackerService trackerService) {
        this.trackerService = trackerService;

        for (TrackerBean trackerBean : this.trackerService.getTrackers()) {
             if (ProjectEnum.JIRA.name().equalsIgnoreCase(trackerBean.getType())) {
                 this.jiraClients.put(trackerBean.getId(), (JiraClient) buildClient(decoder, encoder, client,
                         JiraClient.class, trackerBean.getUrl(), trackerBean.getUser(), trackerBean.getPassword()));
             } else if (ProjectEnum.MANTIS.name().equalsIgnoreCase(trackerBean.getType())) {
                this.mantisClient.put(trackerBean.getId(), (MantisClient) buildClient(decoder, encoder, client,
                        MantisClient.class, trackerBean.getUrl(), trackerBean.getUser(), trackerBean.getPassword()));
            } else if (ProjectEnum.REDMINE.name().equalsIgnoreCase(trackerBean.getType())) {
                this.redmineClient.put(trackerBean.getId(), (RedmineClient) buildClient(decoder, encoder, client,
                        RedmineClient.class, trackerBean.getUrl(), trackerBean.getUser(), trackerBean.getPassword()));
            }
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
        if (projectEntity != null && projectEntity.getId() != null) {
            if (ProjectEnum.JIRA.equals(projectEntity.getId().getTrackerType())) {
                for (Map.Entry<String, JiraClient> entry : trackerRouter.getJiraClients().entrySet()) {
                    if (entry.getKey().equals(projectEntity.getId().getTrackerId())) {
                        return entry.getValue();
                    }
                }
            } else if (ProjectEnum.MANTIS.equals(projectEntity.getId().getTrackerType())) {
                for (Map.Entry<String, MantisClient> entry : trackerRouter.getMantisClient().entrySet()) {
                    if (entry.getKey().equals(projectEntity.getId().getTrackerId())) {
                        return entry.getValue();
                    }
                }
            } else if (ProjectEnum.REDMINE.equals(projectEntity.getId().getTrackerType())) {
                for (Map.Entry<String, RedmineClient> entry : trackerRouter.getRedmineClient().entrySet()) {
                    if (entry.getKey().equals(projectEntity.getId().getTrackerId())) {
                        return entry.getValue();
                    }
                }
            }
        }
        return null;
    }

    public Map<String, JiraClient> getJiraClients() {
        return jiraClients;
    }

    public void setJiraClients(Map<String, JiraClient> jiraClients) {
        this.jiraClients = jiraClients;
    }

    public Map<String, MantisClient> getMantisClient() {
        return mantisClient;
    }

    public void setMantisClient(Map<String, MantisClient> mantisClient) {
        this.mantisClient = mantisClient;
    }

    public Map<String, RedmineClient> getRedmineClient() {
        return redmineClient;
    }

    public void setRedmineClient(Map<String, RedmineClient> redmineClient) {
        this.redmineClient = redmineClient;
    }
}