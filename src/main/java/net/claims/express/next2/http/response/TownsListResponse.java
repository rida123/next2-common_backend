package net.claims.express.next2.http.response;

import lombok.Data;

import java.util.List;
@Data
public class TownsListResponse {
    private List<TownsResponse> townsResponseList ;
}
