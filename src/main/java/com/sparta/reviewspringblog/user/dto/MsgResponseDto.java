package com.sparta.reviewassignment.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MsgResponseDto {

    private String msg;

    public MsgResponseDto(String msg){
        this.msg = msg;
    }


}
