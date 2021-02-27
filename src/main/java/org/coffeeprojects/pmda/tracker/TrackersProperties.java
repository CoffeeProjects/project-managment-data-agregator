package org.coffeeprojects.pmda.tracker;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@ConfigurationProperties
public class TrackersProperties {

    private List<TrackerDataBean> trackers = new ArrayList<>();

    @PostConstruct
    public void init() {
        if (trackers != null && !trackers.isEmpty()) {
            trackers.forEach(p -> {
                String type = p.getType();
                String localId = p.getLocalId();
                String clientId = p.getClientId();
                String url = p.getUrl();
                String user = p.getUser();
                String password = p.getPassword();

                if (StringUtils.isEmpty(type) || StringUtils.isEmpty(localId) || StringUtils.isEmpty(clientId) ||
                        StringUtils.isEmpty(url) || StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
                    throw new ExceptionInInitializerError("Unable to initialize the list of trackers. Please check your configuration file");
                }
            });
        } else {
            throw new ExceptionInInitializerError("No trackers found. Please fill it in the configuration file");
        }
    }

    public List<TrackerDataBean> getTrackers() {
        return this.trackers;
    }
}
