package net.claims.express.next2.http;

import lombok.Getter;

@Getter
public enum StatusCode {

    OK(1, "OK")
        ,FAILED(0,"fAILED")
    ;

    private final int code;
    private final String description;

    StatusCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
