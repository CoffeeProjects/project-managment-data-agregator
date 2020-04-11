package org.coffeeprojects.pmda.feature.project;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(fullyQualifiedNames = "org.coffeeprojects.pmda.*")
public class ProjectUtilsTest {

    @Test
    public void get_client_name_custom_fields_null() {
        assertThat(ProjectUtils.getClientNameCustomFields(null)).isNull();
    }

    @Test
    public void get_client_name_custom_fields_null_custom_fields() {
        ProjectEntity projectEntity = new ProjectEntity();
        assertThat(ProjectUtils.getClientNameCustomFields(projectEntity)).isNull();
    }

    @Test
    public void get_client_name_custom_fields_empty_custom_fields() {
        ProjectCustomField projectCustomField = new ProjectCustomField();
        Set<ProjectCustomField> projectCustomFields = new HashSet();
        projectCustomFields.add(projectCustomField);

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectCustomFields(projectCustomFields);

        assertThat(ProjectUtils.getClientNameCustomFields(projectEntity)).isNull();
    }

    @Test
    public void get_client_name_custom_fields() {
        ProjectCustomField projectCustomField = new ProjectCustomField().setClientName("client_name");
        Set<ProjectCustomField> projectCustomFields = new HashSet();
        projectCustomFields.add(projectCustomField);

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setProjectCustomFields(projectCustomFields);

        assertThat(ProjectUtils.getClientNameCustomFields(projectEntity).get(0)).isEqualTo("client_name");
    }
}
