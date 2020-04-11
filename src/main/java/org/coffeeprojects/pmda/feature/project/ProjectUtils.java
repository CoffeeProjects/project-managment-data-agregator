package org.coffeeprojects.pmda.feature.project;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProjectUtils {

    private static final Logger log = LoggerFactory.getLogger(ProjectUtils.class);

    private ProjectUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> getClientNameCustomFields(ProjectEntity projectEntity) {
        if (projectEntity != null && projectEntity.getProjectCustomFields() != null) {
            List<String> clientNameCustomFields = new ArrayList();
            projectEntity.getProjectCustomFields()
                    .stream()
                    .filter(projectCustomField -> StringUtils.isNotEmpty(projectCustomField.getClientName()))
                    .forEach(projectCustomField -> clientNameCustomFields.add(projectCustomField.getClientName()));
            return clientNameCustomFields.isEmpty() ? null : clientNameCustomFields;
        } else {
            if (projectEntity != null) {
                log.info("No custom fields available for this project : {0}", projectEntity.toString());
            } else {
                log.error("No project provided to get custom fields");
            }
            return null;
        }
    }
}