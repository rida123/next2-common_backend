package net.claims.express.next2.http.response;

import lombok.Data;

import java.io.Serializable;
@Data
public class CompanyResponse implements Serializable {


	private Integer companyId;

	private String companyName;


}
