package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_TOWN")
public class CarsTown implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "TOWN_STATE")
	private String townState;
	
	
	@Column(name = "TOWN_INSURANCE")
	private Integer townInsurance;
	
	
	@Column(name = "TOWN_STATE_ADDRESS")
	private String townStateAddress;
	
	
	@Column(name = "TOWN_REGION")
	private String townRegion;
	
	
	@Column(name = "TOWN_CAZA")
	private String townCaza;
	
	
	@Column(name = "TOWN_CODE")
	private String townCode;
	
	
	@Column(name = "TOWN_TERRITORY")
	private String townTerritory;
	

	
	
	@Column(name = "TOWN_ARABIC_NAME")
	private String townArabicName;
	
	
	@Id
	@Column(name = "TOWN_ID")
	private String townId;
	
	@ManyToOne
	@JoinColumn(name="TOWN_CAZA_ID")
	private CarsCaza carsCaza;
	
	@Column(name = "TOWN_LONGITUDE")
	private Integer townLongitude;
	
	
	@Column(name = "TOWN_LATITUDE")
	private Integer townLatitude;
	
	
	@Column(name = "TOWN_GOOGLE_NAME")
	private String townGoogleName;

	public CarsTown() {
		this.townId = UUID.randomUUID().toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}