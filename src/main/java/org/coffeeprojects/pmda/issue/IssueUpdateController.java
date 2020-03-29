package org.coffeeprojects.pmda.issue;

import org.coffeeprojects.pmda.project.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;

@Controller
@RequestMapping("/issue")
public class IssueUpdateController {

    @Autowired
    IssueService issueService;

    @GetMapping("/update")
    @ResponseBody
    String getProjects() {
        issueService.updateIssues("pmda",
                Instant.parse("2020-03-29T09:15:24.00Z"),"schema,names", "summary,issuetype");
        return "OK";
    }
}