package com.narcispurghel.hrm.domain.dto;

import java.util.UUID;

public class UserDto {

    private UUID id;
    private String username;
    private String password;
    private String name;
    private Integer age;

    public UserDto() {

    }

    public UserDto(String username, String password, String name, Integer age) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public UserDto(UUID id,
                   String username,
                   String password,
                   String name, Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
