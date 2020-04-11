package org.coffeeprojects.pmda.feature.project;

import java.util.ArrayList;
import java.util.List;

public class ProjectUtils {

    public static List<String> getClientNameCustomFields(ProjectEntity projectEntity) {
        List<String> clientNameCustomFields = new ArrayList();
        projectEntity.getProjectCustomFields().forEach(projectCustomField -> {
            clientNameCustomFields.add(projectCustomField.getClientName());
        });
        return clientNameCustomFields;
    }
}
