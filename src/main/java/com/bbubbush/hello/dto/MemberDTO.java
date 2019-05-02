package com.bbubbush.hello.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MemberDTO {
    private int no;

    private String name;
}
