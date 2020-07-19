package org.coffeeprojects.pmda.feature.issue.jirabean;

public class TimeTrackingJiraBean {

    private Integer originalEstimateSeconds;

    private Integer remainingEstimateSeconds;

    private Integer timeSpentSeconds;

    public Integer getOriginalEstimateSeconds() {
        return originalEstimateSeconds;
    }

    public TimeTrackingJiraBean setOriginalEstimateSeconds(Integer originalEstimateSeconds) {
        this.originalEstimateSeconds = originalEstimateSeconds;
        return this;
    }

    public Integer getRemainingEstimateSeconds() {
        return remainingEstimateSeconds;
    }

    public TimeTrackingJiraBean setRemainingEstimateSeconds(Integer remainingEstimateSeconds) {
        this.remainingEstimateSeconds = remainingEstimateSeconds;
        return this;
    }

    public Integer getTimeSpentSeconds() {
        return timeSpentSeconds;
    }

    public TimeTrackingJiraBean setTimeSpentSeconds(Integer timeSpentSeconds) {
        this.timeSpentSeconds = timeSpentSeconds;
        return this;
    }

    @Override
    public String toString() {
        return "TimeTrackingJiraBean{" +
                "originalEstimateSeconds=" + originalEstimateSeconds +
                ", remainingEstimateSeconds=" + remainingEstimateSeconds +
                ", timeSpentSeconds=" + timeSpentSeconds +
                '}';
    }
}
