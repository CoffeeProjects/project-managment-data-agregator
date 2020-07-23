package org.coffeeprojects.pmda.batch;

public enum BatchName {
    JOB_PROJECT_UPDATE("PROJECT UPDATE JOB"),
    JOB_PROJECT_RETRY("PROJECT RETRY JOB");

    private String name;

    BatchName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
