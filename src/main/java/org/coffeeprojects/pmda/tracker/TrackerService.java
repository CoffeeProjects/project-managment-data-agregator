package org.coffeeprojects.pmda.tracker;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties
public class TrackerService {

    private List<TrackerBean> trackers = new ArrayList();

    @PostConstruct
    public void init() {
        this.getTrackers().forEach(p -> {
            String type = p.getType();
            String number = p.getId();
            String url = p.getUrl();
            String user = p.getUser();
            String password = p.getPassword();

            if (StringUtils.isEmpty(type) || StringUtils.isEmpty(type) || StringUtils.isEmpty(number) ||
                    StringUtils.isEmpty(url) || StringUtils.isEmpty(user) || StringUtils.isEmpty(password)) {
                trackers.remove(p);
            }
        });
    }

    public List<TrackerBean> getTrackers() {
        return this.trackers;
    }
}