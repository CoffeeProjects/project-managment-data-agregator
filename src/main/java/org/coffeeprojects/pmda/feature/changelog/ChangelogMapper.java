package org.coffeeprojects.pmda.feature.changelog;

import org.coffeeprojects.pmda.entity.CompositeIdBaseEntity;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ChangelogJiraBean;
import org.coffeeprojects.pmda.feature.changelog.jirabean.ItemHistoryJiraBean;
import org.coffeeprojects.pmda.feature.user.UserEntity;
import org.coffeeprojects.pmda.feature.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface ChangelogMapper {

    String ID_DATE_FORMAT = "yyyyMMddHHmmss";
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    ChangelogEntity toEntity(ChangelogJiraBean changelogJiraBean);
    ChangelogEntity toEntity(ItemHistoryJiraBean itemHistoryJiraBean);

    default Set<ChangelogEntity> toEntities(ChangelogJiraBean changelogJiraBean) {
        return Optional.ofNullable(changelogJiraBean)
                .stream()
                .flatMap(c -> c.getHistories().stream())
                .flatMap(h -> {
                        AtomicInteger itemCount = new AtomicInteger();
                        UserEntity authorEntity = Optional.ofNullable(h.getAuthor()).map(USER_MAPPER::toEntity)
                                .map(a -> a.setId(new CompositeIdBaseEntity().setClientId(h.getAuthor().getAccountId()))).orElse(null);

                        return h.getItems().stream().map(i -> {
                            ChangelogEntity changelogEntity = toEntity(i);
                            changelogEntity.setId(generateChangelogId(h.getId(), h.getCreated(), itemCount.getAndIncrement()));
                            changelogEntity.setAuthor(authorEntity);
                            changelogEntity.setCreated(h.getCreated());
                            return changelogEntity;
                        });
                }).collect(Collectors.toSet());
    }

    private CompositeIdBaseEntity generateChangelogId(String id, Date date, int itemCount) {
        SimpleDateFormat formatter = new SimpleDateFormat(ID_DATE_FORMAT);
        return new CompositeIdBaseEntity().setClientId(id + "_" + formatter.format(date) + "_" + itemCount);
    }
}