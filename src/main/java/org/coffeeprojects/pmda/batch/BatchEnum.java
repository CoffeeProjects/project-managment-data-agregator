package org.coffeeprojects.pmda.batch;

public enum BatchEnum {
    JOB_PROJECT_UPDATE("PROJECT UPDATE JOB");

    private String name;

    BatchEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }
}
