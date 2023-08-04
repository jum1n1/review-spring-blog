package com.sparta.reviewassignment.user.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;


@Getter
@Setter
public class SignupRequestDto {

    @Pattern(regexp = "^[a-zA-Z0-9]{3}$")
    private String nickName;

    @Pattern(regexp = "^{4}$")
    private String password;

    @Pattern(regexp = "^{4}$")
    private String checkPassword;

}