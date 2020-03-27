package org.coffeeprojects.pmda.domain;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Priority implements Serializable {
    @Id
    private String id;

    private String name;
}