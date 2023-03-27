package org.example;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity // JPA 사용하는 거라고 인식하는 어노테이션
public class Member {

    @Id
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
