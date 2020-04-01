package org.coffeeprojects.pmda.issue.quartz;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.quartz.CronExpression;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class IssueUpdateQuartzConfigTest {

    @Test
    public void cron_expression_is_valid() throws ParseException {
        String exp = "0 0/1 * 1/1 * ? *";
        boolean isValidExpression = CronExpression.isValidExpression(exp);

        Calendar date = new GregorianCalendar(2020, 1, 1, 23, 00,00);
        Calendar expectedDate = new GregorianCalendar(2020, 1, 1, 23, 01,00);

        CronExpression cronExpression = new CronExpression(exp);
        cronExpression.getNextValidTimeAfter(date.getTime());

        assertThat(cronExpression.getNextValidTimeAfter(date.getTime()).getTime()).isEqualTo(expectedDate.getTime().getTime());
        assertThat(isValidExpression).isTrue();
    }
}
