package com.group.libraryapp.dto.user;

public class UserCreateRequest {
    private Long id;
    private String name;
    private Integer age;

    public Long getId(){return id;}
    public String getName() {
        return name;
    }
    public Integer getAge() {
        return age;
    }
}
