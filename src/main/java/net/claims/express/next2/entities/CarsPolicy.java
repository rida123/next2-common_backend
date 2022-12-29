package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_POLICY")

public class CarsPolicy extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "POLICY_STATE")
	private java.lang.String policyState;
	
	
	@Column(name = "POLICY_INSURANCE")
	private java.lang.Integer policyInsurance;
	
	
	@Column(name = "POLICY_BRANCH_OLD")
	private java.lang.Integer policyBranchOld;
	
	
	@Column(name = "POLICY_LOB")
	private java.lang.String policyLob;
	
	
	@Column(name = "POLICY_NUMBER_OLD")
	private java.lang.Integer policyNumberOld;
	
	
	@Column(name = "POLICY_CAR")
	private java.lang.Integer policyCar;
	
	
	@Column(name = "POLICY_AMENDMENT")
	private java.lang.Integer policyAmendment;
	
	
	@Column(name = "POLICY_PRODUCT")
	private java.lang.String policyProduct;
	
	
	@Column(name = "POLICY_CLIENT")
	private java.lang.Integer policyClient;
	
	
	@Column(name = "POLICY_SECOND_CLIENT")
	private java.lang.Integer policySecondClient;
	
	
	@Column(name = "POLICY_BROKER_NUM_OLD")
	private java.lang.Integer policyBrokerNumOld;
	
	
	@Column(name = "POLICY_REMARKS")
	private java.lang.String policyRemarks;
	
	
	@Column(name = "POLICY_ISSUE_DATEJ")
	private java.lang.Integer policyIssueDatej;
	
	
	@Column(name = "POLICY_EFFECTIVE_DATEJ")
	private java.lang.Integer policyEffectiveDatej;
	
	
	@Column(name = "POLICY_EXPIRY_DATEJ")
	private java.lang.Integer policyExpiryDatej;
	
	
	@Column(name = "POLICY_PRIMUM")
	private java.lang.Integer policyPrimum;
	
	
	@Column(name = "POLICY_CURRENCY")
	private java.lang.String policyCurrency;
	
	
	@Column(name = "POLICY_ACTION")
	private java.lang.String policyAction;
	
	
	@Column(name = "POLICY_AMENDMENT_DATEJ")
	private java.lang.Integer policyAmendmentDatej;
	
	
	@Column(name = "POLICY_DATE_DOCUMENTJ")
	private java.lang.Integer policyDateDocumentj;
	
	
	@Column(name = "POLICY_UWY")
	private java.lang.Integer policyUwy;
	
	
	@Column(name = "POLICY_FLEET")
	private java.lang.String policyFleet;
	
	
	@Column(name = "POLICY_PRODUCT_DATEJ")
	private java.lang.Integer policyProductDatej;
	
	@Column(name = "POLICY_CLAUSES")
	private java.lang.String policyClauses;
	
	
	@Column(name = "POLICY_BROKER_BRANCH")
	private java.lang.String policyBrokerBranch;
	
	
	@Column(name = "POLICY_BROKER")
	private java.lang.String policyBroker;
	
	
	@Column(name = "POLICY_CAR_OWNER")
	private java.lang.String policyCarOwner;
	
	
	@Column(name = "POLICY_SECOND_CAR_OWNER")
	private java.lang.String policySecondCarOwner;
	
	
	@Column(name = "POLICY_ID_INS")
	private java.lang.String policyIdIns;
	
	
	@Column(name = "POLICY_SUBLINE")
	private java.lang.String policySubline;
	
	
	@Column(name = "POLICY_PLAN")
	private java.lang.String policyPlan;
	
	
	@Column(name = "POLICY_COVER")
	private java.lang.String policyCover;
	
	
	@Column(name = "POLICY_ENDORS1")
	private java.lang.Integer policyEndors1;
	
	
	@Column(name = "POLICY_ENDORS2")
	private java.lang.Integer policyEndors2;
	
	
	@Column(name = "POLICY_AGENCY_REPAIR")
	private java.lang.String policyAgencyRepair;
	
	
	@Column(name = "POLICY_CAR_REPLACEMENT")
	private java.lang.String policyCarReplacement;
	
	
	@Column(name = "POLICY_COMPANY")
	private java.lang.String policyCompany;
	
	
	@Column(name = "POLICY_PRODUCT_ID")
	private java.lang.String policyProductId;
	
	
	@Id
	@Column(name = "POLICY_ID")
	private java.lang.String policyId;
	
	
	@Column(name = "POLICY_PRODUCTS_ID")
	private java.lang.String policyProductsId;
	
	
	@Column(name = "POLICY_CLIENT_ID")
	private java.lang.String policyClientId;
	
	
	@Column(name = "POLICY_TOW_FLAG")
	private java.lang.String policyTowFlag;
	
	
	@Column(name = "POLICY_SPECIAL_FLAG")
	private java.lang.String policySpecialFlag;
	
	
	@Column(name = "POLICY_FACULTATIF")
	private java.lang.String policyFacultatif;
	
	
	@Column(name = "POLICY_DEDUCTIBLE_AMT")
	private java.lang.Integer policyDeductibleAmt;
	
	
	@Column(name = "POLICY_REPLACEMENT_FLAG")
	private java.lang.String policyReplacementFlag;
	
	
	@Column(name = "POLICY_REPLACEMENT_AMOUNT")
	private java.lang.Integer policyReplacementAmount;
	
	
	@Column(name = "POLICY_REPLACEMENT_CURRENCY")
	private java.lang.String policyReplacementCurrency;
	
	
	@Column(name = "POLICY_REPLACEMENT_NUMBER_DAYS")
	private java.lang.Integer policyReplacementNumberDays;
	
	
	@Column(name = "POLICY_REPLACEMENT_DED_NBRDAYS")
	private java.lang.Integer policyReplacementDedNbrdays;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_NEW_DEDUCTIBLE_DATE")
	private java.util.Date policyNewDeductibleDate;
	
	
	@Column(name = "POLICY_PRODUCT_2ND_NAMING")
	private java.lang.String policyProduct2ndNaming;
	
	
	@Column(name = "POLICY_RESP")
	private java.lang.Integer policyResp;
	
	
	
	@ManyToOne
	@JoinColumn(name="POLICY_BRANCH_ID")
	private CarsBranch carsBranch;
	
	
	@ManyToOne
	@JoinColumn(name="POLICY_BROKER_ID")
	private CarsBroker carsBroker;
	
	
	@ManyToOne
	@JoinColumn(name="POLICY_INSURANCE_ID")
	private CarsInsurance carsInsurance;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_ISSUE_DATE")
	private java.util.Date policyIssueDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_EFFECTIVE_DATE")
	private java.util.Date policyEffectiveDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_EXPIRY_DATE")
	private java.util.Date policyExpiryDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_AMENDMENT_DATE")
	private java.util.Date policyAmendmentDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_DATE_DOCUMENT")
	private java.util.Date policyDateDocument;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "POLICY_PRODUCT_DATE")
	private java.util.Date policyProductDate;
	
	
	@Column(name = "POLICY_BRANCH")
	private java.lang.String policyBranch;
	
	
	@Column(name = "POLICY_NUMBER")
	private java.lang.String policyNumber;
	
	
	@Column(name = "POLICY_BROKER_NUM")
	private java.lang.String policyBrokerNum;
	
	@Column(name = "POLICY_DEDUCTIBLE")
	private java.lang.String policyDeductible;
	
	
	@Column(name = "POLICY_BROKER_WEB_ORIGIN")
	private java.lang.String policyBrokerWebOrigin;
	
	
	@Column(name = "POLICY_IS_DIRECT_BROKER")
	private java.lang.String policyIsDirectBroker;
	
	
	@Column(name = "POLICY_WEB")
	private java.lang.String policyWeb;
	
	
	@Column(name = "POLICY_ISSUING_USER")
	private java.lang.String policyIssuingUser;
	
	
	@Column(name = "POLICY_MODIFIED_BY_USER")
	private java.lang.String policyModifiedByUser;
	
	
	@Column(name = "POLICY_BLACKLIST_SET_ON")
	private java.lang.String policyBlacklistSetOn;
	
	
	@Column(name = "POLICY_BLACKLIST_SET_BY")
	private java.lang.String policyBlacklistSetBy;
	
	
	@Column(name = "POLICY_PREMIUM_CURRENCY")
	private java.lang.String policyPremiumCurrency;
	
	
	@Column(name = "POLICY_PREMIUM_CURRENCY_RATE")
	private java.lang.Integer policyPremiumCurrencyRate;
	
	
	@Column(name = "POLICY_SUM_INS_CURRENCY_RATE")
	private java.lang.Integer policySumInsCurrencyRate;
	
	
	@Column(name = "POLICY_INSURANCE_BL_SET_ON")
	private java.lang.String policyInsuranceBlSetOn;
	
	
	@Column(name = "POLICY_INSURANCE_BL_SET_BY")
	private java.lang.String policyInsuranceBlSetBy;
	
	
	@Column(name = "POLICY_BROKER_BL_SET_ON")
	private java.lang.String policyBrokerBlSetOn;
	
	
	@Column(name = "POLICY_BROKER_BL_SET_BY")
	private java.lang.String policyBrokerBlSetBy;
	
	
	@Column(name = "POLICY_PRINT_NAME")
	private java.lang.String policyPrintName;
	
	
	
	@ManyToOne
	@JoinColumn(name="POLICY_SUBLINE_ID")
	private CarsSubline carsSubline;
	
	@Column(name = "POLICY_END_AT_NOON")
	private java.lang.String policyEndAtNoon;
	
	
	@Column(name = "POLICY_NUMBER_DISPLAY")
	private java.lang.String policyNumberDisplay;
	
	
	@Column(name = "POLICY_SPECIAL_NOTES")
	private java.lang.String policySpecialNotes;
	
	
	public CarsPolicy() {
		this.policyId = UUID.randomUUID().toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}