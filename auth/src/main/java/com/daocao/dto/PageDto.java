package com.daocao.dto;

import lombok.Data;

@Data
public class PageDto {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    private String username;

    private String nickname;

    private String mobile;
}
