package org.coffeeprojects.pmda.issue;

import org.mapstruct.Mapper;

import java.util.List;

//@Mapper
public interface IssueMapper {

    // TODO: à mapper
    IssueEntity toEntity(IssueJiraBean issueJiraBean);

    List<IssueEntity> toEntities(List<IssueJiraBean> issueJiraBean);
}
