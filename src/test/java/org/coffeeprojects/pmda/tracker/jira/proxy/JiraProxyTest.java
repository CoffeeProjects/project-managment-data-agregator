package org.coffeeprojects.pmda.tracker.jira.proxy;

import org.coffeeprojects.pmda.feature.issue.jirabean.IssueJiraBean;
import org.coffeeprojects.pmda.feature.issue.jirabean.SearchIssuesResultJiraBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import wiremock.com.github.jknack.handlebars.internal.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 8086)
public class JiraProxyTest {
    @Autowired
    private JiraProxy jiraProxy;

    @Test
    public void search_issues_should_issues() throws IOException {
        // Given
        String jql = "project in (PMDA) AND updated >= \"2020-03-29 10:09\"";
        String fields = "key";
        String expand = "changelog";
        String maxResults = "50";
        String startAt = "0";

        ClassLoader classLoader = this.getClass().getClassLoader();
        File file = new File(classLoader.getResource("data/search-issues-result.json").getFile());
        String jsonResult = Files.read(file, StandardCharsets.UTF_8);

        stubFor(get(urlPathMatching("/search"))
                .withQueryParam("query", equalTo(jql))
                .withQueryParam("expand", equalTo(expand))
                .withQueryParam("fields", equalTo(fields))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBody(jsonResult))
        );

        // When
        SearchIssuesResultJiraBean searchIssuesResultJiraBean = jiraProxy.searchIssues(jql, expand, fields, maxResults, startAt);

        // Then
        assertThat(searchIssuesResultJiraBean).isNotNull();
        assertThat(searchIssuesResultJiraBean.getExpand()).isEqualTo("schema,names");
        assertThat(searchIssuesResultJiraBean.getStartAt()).isEqualTo(0);
        assertThat(searchIssuesResultJiraBean.getMaxResults()).isEqualTo(50);
        assertThat(searchIssuesResultJiraBean.getTotal()).isEqualTo(3);
        assertThat(searchIssuesResultJiraBean.getIssues()).hasSize(3);
        assertThat(searchIssuesResultJiraBean.getIssues()).extracting(IssueJiraBean::getId).containsExactly("10002", "10001", "10000");
        assertThat(searchIssuesResultJiraBean.getIssues()).extracting(IssueJiraBean::getKey).containsExactly("PMDA-3", "PMDA-2", "PMDA-1");
        assertThat(searchIssuesResultJiraBean.getIssues()).extracting(IssueJiraBean::getExpand).containsOnly("operations,versionedRepresentations,editmeta,changelog,renderedFields");
        // TODO: verifier les fields
    }
}
