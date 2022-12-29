package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_SERVICES")

public class CarsServices extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "SERVICE_ID")
	private java.lang.String serviceId;
	
	
	@Column(name = "SERVICE_DESCRIPTION")
	private java.lang.String serviceDescription;
	
	
	public CarsServices() {
		this.serviceId = UUID.randomUUID().toString();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}