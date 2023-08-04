package com.example.homework4.dto;

import com.example.homework4.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$",
            message = "최소 4자 이상, 10자 이하의 알파벳 소문자와, 숫자로 구성되어야 한다 게로!")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&*()_+{}:\"<>?,.\\\\/]{8,15}$",
            message = "최소 8자 이상, 15자 이하의 알파벳 대소문자, 숫자, 특수문자로 구성되어야 한다 게로!")
    private String password;

    private String nickname;

    private UserRoleEnum role;


}
