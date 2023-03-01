package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_SURVEYOR_PLACE")

public class CarsSurveyorPlace extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SURVEYOR_NUM")
	private String surveyorNum;
	
	
	@Column(name = "SURVEYOR_ORDER")
	private Integer surveyorOrder;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SURVEYOR_AS_DATE")
	private java.util.Date surveyorAsDate;
	
	
	@Column(name = "SURVEYOR_REMARKS")
	private String surveyorRemarks;
	
	
	@Column(name = "SURVEYOR_REGION")
	private String surveyorRegion;
	
	
	@Column(name = "SURVEYOR_CAZA")
	private String surveyorCaza;
	
	
	@Column(name = "SURVEYOR_TERRITORY")
	private String surveyorTerritory;
	
	@Column(name = "SURVEYOR_COMPANY")
	private String surveyorCompany;
	
	
	@Column(name = "SURVEYOR_DEALER")
	private String surveyorDealer;
	
	
	@Id
	@Column(name = "SURVEYOR_PLACE_ID")
	private String surveyorPlaceId;
	
	
	
	@ManyToOne
	@JoinColumn(name="SURVEYOR_ID")
	private CarsSupplier carsSupplier;
	
	
	@ManyToOne
	@JoinColumn(name="SURVEYOR_REGION_ID")
	private CarsRegion carsRegion;
	
	
	@ManyToOne
	@JoinColumn(name="SURVEYOR_CAZA_ID")
	private CarsCaza carsCaza;
	
	@Column(name = "SURVEYOR_INCURANCE_ID")
	private String surveyorIncuranceId;
	

	
	public CarsSurveyorPlace() {
		this.surveyorPlaceId = UUID.randomUUID().toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}