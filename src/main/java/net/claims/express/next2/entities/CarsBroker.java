package net.claims.express.next2.entities;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_BROKER")
public class CarsBroker extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "BROKER_STATE")
	private java.lang.String brokerState;
	
	
	@Column(name = "BROKER_INSURANCE")
	private java.lang.String brokerInsurance;
	
	
	@Column(name = "BROKER_NUM")
	private java.lang.String brokerNum;
	
	
	@Column(name = "BROKER_DESC")
	private java.lang.String brokerDesc;
	
	
	@Column(name = "BROKER_CONTACT_INFO")
	private java.lang.String brokerContactInfo;
	
	
	@Column(name = "BROKER_TELEPHONE")
	private java.lang.String brokerTelephone;
	
	
	@Column(name = "BROKER_DEPR")
	private java.lang.Integer brokerDepr;

	@Column(name = "BROKER_BRANCH")
	private java.lang.String brokerBranch;
	
	@Id
	@Column(name = "BROKER_ID")
	private java.lang.String brokerId;
	
	@ManyToOne
	@JoinColumn(name="BROKER_INSURANCE_ID")
	private CarsInsurance carsInsurance;
	
	@Column(name = "BROKER_DESCRIPTION")
	private java.lang.String brokerDescription;
	
	@Column(name = "BROKER_REFFERAL")
	private java.lang.String brokerRefferal;
	
	
	@Column(name = "BROKER_REFFERAL_NOTE")
	private java.lang.String brokerRefferalNote;
	
	
	@Column(name = "BROKER_FEES")
	private java.lang.String brokerFees;
	
	
	@Column(name = "BROKER_REFERENCE")
	private java.lang.String brokerReference;
	
	

	public CarsBroker() {
		this.brokerId = UUID.randomUUID().toString();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}