package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_REGION")

public class CarsRegion extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "REGION_STATE")
	private String regionState;
	
	
	@Column(name = "REGION_INSURANCE")
	private Integer regionInsurance;
	
	
	@Column(name = "REGION_STATE_ADDRESS")
	private String regionStateAddress;
	
	
	@Column(name = "REGION_CODE")
	private String regionCode;
	
	
	@Column(name = "REGION_NAME")
	private String regionName;

	
	@Column(name = "REGION_ARABIC_NAME")
	private String regionArabicName;
	
	
	@Id
	@Column(name = "REGION_ID")
	private String regionId;
	
	
	public CarsRegion() {
		this.regionId = UUID.randomUUID().toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}