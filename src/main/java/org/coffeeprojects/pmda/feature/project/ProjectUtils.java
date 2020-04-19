package org.coffeeprojects.pmda.feature.project;

import org.apache.commons.lang3.StringUtils;
import org.coffeeprojects.pmda.feature.user.LocaleJiraBean;
import org.coffeeprojects.pmda.tracker.jira.JiraClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjectUtils {

    private static final Logger log = LoggerFactory.getLogger(ProjectUtils.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";

    private ProjectUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getClientNameCustomFields(ProjectEntity projectEntity) {
        List<String> clientNameCustomFields = new ArrayList();
        if (projectEntity != null && projectEntity.getProjectCustomFields() != null) {

            projectEntity.getProjectCustomFields()
                    .stream()
                    .filter(projectCustomField -> StringUtils.isNotEmpty(projectCustomField.getClientName()))
                    .forEach(projectCustomField -> clientNameCustomFields.add(projectCustomField.getClientName()));
        } else {
            log.info("No custom fields available for this project : {}", projectEntity);
        }
        return clientNameCustomFields;
    }

    public static String getLastCheckWithLocale(Locale locale, Instant lastCheck) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT).withLocale(locale);
        return dateTimeFormatter.format(lastCheck);
    }
}
