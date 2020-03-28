package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.component.ComponentJiraBean;
import org.coffeeprojects.pmda.issueType.IssueTypeJiraBean;
import org.coffeeprojects.pmda.priority.PriorityJiraBean;
import org.coffeeprojects.pmda.project.ProjectJiraBean;
import org.coffeeprojects.pmda.resolution.ResolutionJiraBean;
import org.coffeeprojects.pmda.status.StatusJiraBean;
import org.coffeeprojects.pmda.user.UserJiraBean;
import org.coffeeprojects.pmda.version.VersionJiraBean;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class IssueJiraBean {

    private String id;

    private String key;

    private String expand;

    private FieldsJiraBean fields;

    public String getId() {
        return id;
    }

    public IssueJiraBean setId(String id) {
        this.id = id;
        return this;
    }

    public String getKey() {
        return key;
    }

    public IssueJiraBean setKey(String key) {
        this.key = key;
        return this;
    }

    public String getExpand() {
        return expand;
    }

    public IssueJiraBean setExpand(String expand) {
        this.expand = expand;
        return this;
    }

    public FieldsJiraBean getFields() {
        return fields;
    }

    public IssueJiraBean setFields(FieldsJiraBean fields) {
        this.fields = fields;
        return this;
    }
}
