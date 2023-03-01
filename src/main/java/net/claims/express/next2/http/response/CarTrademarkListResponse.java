package net.claims.express.next2.http.response;

import lombok.Data;

import java.util.List;
@Data
public class CarTrademarkListResponse  {

	

	private List<CarTrademarkResponse> carTrademarkBeanList;
	List<String> immutableList = List.of("one","two","three");

	
}
