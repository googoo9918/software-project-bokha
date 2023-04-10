package com.app.global.jwt.constant;

import lombok.Getter;

@Getter
public enum GrantType {

    BEARER("Bearer");
    //BEARER는 JWT 또는 OAuth 토큰을 사용한다는 의미
    GrantType(String type) {
        this.type = type;
    }

    private String type;

}
