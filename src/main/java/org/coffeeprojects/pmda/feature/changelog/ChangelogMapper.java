package org.coffeeprojects.pmda.feature.changelog;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ChangelogJiraBean;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ItemHistoryJiraBean;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserJiraBean;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ChangelogMapper {

    String ID_DATE_FORMAT = "yyyyMMddHHmmss";
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    ChangelogEntity toEntity(ChangelogJiraBean changelogJiraBean);
    ChangelogEntity toEntity(ItemHistoryJiraBean itemHistoryJiraBean);

    default Set<ChangelogEntity> toEntities(ChangelogJiraBean changelogJiraBean) {
        Set<ChangelogEntity> changelogEntities = new HashSet<>();
        if (changelogJiraBean != null && changelogJiraBean.getHistories() != null) {
            changelogJiraBean.getHistories().forEach(h -> {
                AtomicInteger itemCount = new AtomicInteger();
                UserJiraBean authorJiraBean = h.getAuthor();
                if (authorJiraBean != null && h.getItems() != null) {
                    UserEntity authorEntity = USER_MAPPER.toEntity(authorJiraBean);
                    authorEntity.setId(new CompositeIdBaseEntity().setClientId(authorJiraBean.getAccountId()));
                    h.getItems().forEach(i -> {
                        ChangelogEntity changelogEntity = toEntity(i);
                        changelogEntity.setId(generateChangelogId(h.getId(), h.getCreated(), itemCount.getAndIncrement()));
                        changelogEntity.setAuthor(authorEntity);
                        changelogEntity.setCreated(h.getCreated());
                        changelogEntities.add(changelogEntity);
                    });
                }
            });
        }
        return changelogEntities;
    }

    private CompositeIdBaseEntity generateChangelogId(String id, Date date, int itemCount) {
        SimpleDateFormat formatter = new SimpleDateFormat(ID_DATE_FORMAT);
        return new CompositeIdBaseEntity().setClientId(id + "_" + formatter.format(date) + "_" + itemCount);
    }
}