package org.coffeeprojects.pmda.feature.project;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ProjectUtils {

    private static final Logger log = LoggerFactory.getLogger(ProjectUtils.class);

    public static List<String> getClientNameCustomFields(ProjectEntity projectEntity) {
        if (projectEntity != null && projectEntity.getProjectCustomFields() != null) {
            List<String> clientNameCustomFields = new ArrayList();
            projectEntity.getProjectCustomFields()
                    .stream()
                    .filter(projectCustomField -> StringUtils.isNotEmpty(projectCustomField.getClientName()))
                    .forEach(projectCustomField -> {
                        clientNameCustomFields.add(projectCustomField.getClientName());
            });
            return clientNameCustomFields.size() > 0 ? clientNameCustomFields : null;
        } else {
            if (projectEntity != null) {
                log.info("No custom fields available for this projet : " + projectEntity.toString());
            } else {
                log.error("No project provided to get custom fields");
            }
            return null;
        }
    }
}
