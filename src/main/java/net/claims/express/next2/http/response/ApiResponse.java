package net.claims.express.next2.http.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private int statusCode;
    private String title;
    private String message;
    private Object data;
}

