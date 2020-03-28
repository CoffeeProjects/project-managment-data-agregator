package org.coffeeprojects.pmda.controller.impl;

import org.coffeeprojects.pmda.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/update")
public class UpdateControllerImpl {

    @Autowired
    ProjectsService projectsService;

    @GetMapping("/projects")
    @ResponseBody
    String getProjects() {
        projectsService.getById();
        return "OK";
    }
}