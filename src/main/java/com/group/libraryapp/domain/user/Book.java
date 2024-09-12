package com.group.libraryapp.domain.user;

import javax.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 25, name = "name")
    private String name;

    public Book(String name) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 입력(%s)입니다",name));
        }
        this.name = name;
    }

    protected Book(){}

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
