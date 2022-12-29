package net.claims.express.next2.entities;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_POLICY_CAR")

public class CarsPolicyCar extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "CAR_STATE")
	private java.lang.String carState;
	
	
	@Column(name = "CAR_INSURANCE")
	private java.lang.Integer carInsurance;
	
	
	@Column(name = "CAR_BRANCH")
	private java.lang.Integer carBranch;
	
	
	@Column(name = "CAR_LOB")
	private java.lang.String carLob;
	
	
	@Column(name = "CAR_POLICY_NUMBER")
	private java.lang.Integer carPolicyNumber;
	
	
	@Column(name = "CAR_CAR")
	private java.lang.Integer carCar;
	
	
	@Column(name = "CAR_AMENDMENT")
	private java.lang.Integer carAmendment;
	
	
	@Column(name = "CAR_OBJECT_NUMBER")
	private java.lang.Integer carObjectNumber;
	
	
	@Column(name = "CAR_YEAR")
	private java.lang.Integer carYear;
	
	
	@Column(name = "CAR_CHASSIS")
	private java.lang.String carChassis;
	
	
	@Column(name = "CAR_BRAND_CODE")
	private java.lang.String carBrandCode;
	
	
	@Column(name = "CAR_TRADEMARK_CODE")
	private java.lang.String carTrademarkCode;
	
	
	@Column(name = "CAR_SHAPE_CODE")
	private java.lang.String carShapeCode;
	
	
	@Column(name = "CAR_TYPE")
	private java.lang.String carType;
	
	
	@Column(name = "CAR_USAGE_CODE")
	private java.lang.String carUsageCode;
	
	
	@Column(name = "CAR_PLATE")
	private java.lang.String carPlate;
	
	
	@Column(name = "CAR_COLOR")
	private java.lang.String carColor;
	
	@Column(name = "CAR_DEPRECIATION")
	private java.lang.String carDepreciation;
	
	
	@Column(name = "CAR_TP_DEPR")
	private java.lang.String carTpDepr;
	
	
	@Column(name = "CAR_BRAND_DESC")
	private java.lang.String carBrandDesc;
	
	
	@Column(name = "CAR_TRADEMARK_DESC")
	private java.lang.String carTrademarkDesc;
	
	
	@Column(name = "CAR_USAGE")
	private java.lang.String carUsage;
	
	
	@Column(name = "CAR_ENGINE")
	private java.lang.String carEngine;
	
	
	@Column(name = "CAR_DEDUCTIBLE")
	private java.lang.String carDeductible;
	
	
	@Column(name = "CAR_THEFT_DEDUCTIBLE")
	private java.lang.String carTheftDeductible;
	
	
	@Column(name = "CAR_HOLDUP_DEDUCTIBLE")
	private java.lang.String carHoldupDeductible;
	
	
	@Column(name = "CAR_PRODUCT_DESCRIPTION")
	private java.lang.String carProductDescription;
	
	
	@Column(name = "CAR_VIP")
	private java.lang.String carVip;
	
	
	@Id
	@Column(name = "CAR_ID")
	private java.lang.String carId;
	
	
	
	@ManyToOne
	@JoinColumn(name="POLICY_ID")
	private CarsPolicy carsPolicy;
	
	
	@ManyToOne
	@JoinColumn(name="SHAPE_ID")
	private CarsCarShape carsCarShape;
	
	@Column(name = "CAR_VALUE")
	private java.lang.Integer carValue;
	
	
	@Column(name = "BRAND_ID")
	private java.lang.String brandId;
	
	
	@Column(name = "TRADEMARK_ID")
	private java.lang.String trademarkId;
	
	
	@Column(name = "CAR_BENEFICIARY")
	private java.lang.String carBeneficiary;
	
	
	@Column(name = "CAR_BENEFICIARY_DESC")
	private java.lang.String carBeneficiaryDesc;
	
	@Column(name = "CAR_PLATE_CHAR")
	private java.lang.String carPlateChar;
	
	
	@Column(name = "CAR_PLATE_LENGTH")
	private java.lang.Integer carPlateLength;
	
	
	@Column(name = "CAR_PLATE_NUM")
	private java.lang.String carPlateNum;
	
	
	@Column(name = "CAR_UNPAID_PREMIUM")
	private java.lang.String carUnpaidPremium;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CAR_UNPAID_PREMIUM_DATE")
	private java.util.Date carUnpaidPremiumDate;
	
	
	@Column(name = "CAR_DEALER_WORKSHOP_NAME")
	private java.lang.String carDealerWorkshopName;
	
	public CarsPolicyCar() {
		this.carId = UUID.randomUUID().toString();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}