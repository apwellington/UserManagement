package com.gibittec.swagertest.app.resource.dto;

import lombok.Data;

@Data // genera set y get
public class UserDto {
    private String email;
    private String username;
    private String password;
}
