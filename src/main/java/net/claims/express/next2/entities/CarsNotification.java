package net.claims.express.next2.entities;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_NOTIFICATION")

public class CarsNotification extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "NOTIFICATION_INSURANCE")
	private java.lang.Integer notificationInsurance;
	
	
	@Column(name = "NOTIFICATION_VISA")
	private java.lang.Integer notificationVisa;
	
	
	@Id
	@Column(name = "NOTIFICATION_ID")
	private java.lang.String notificationId;
	
	
	@Column(name = "NOTIFICATION_REPORTED_DATEJ")
	private java.lang.Integer notificationReportedDatej;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTIFICATION_REPORTED_TIME")
	private java.util.Date notificationReportedTime;
	
	
	@Column(name = "NOTIFICATION_CONTACT_NAME")
	private java.lang.String notificationContactName;
	
	
	@Column(name = "NOTIFICATION_CONTACT_PHONE")
	private java.lang.String notificationContactPhone;
	
	
	@Column(name = "NOTIFICATIONS_STATUS")
	private java.lang.String notificationsStatus;
	
	
	@Column(name = "NOTIFICATION_STATUS_DATEJ")
	private java.lang.Integer notificationStatusDatej;
	
	
	@Column(name = "NOTIFICATION_MAT_DAMAGE")
	private java.lang.String notificationMatDamage;
	
	
	@Column(name = "NOTIFICATION_POL_CAR_ID1")
	private java.lang.String notificationPolCarId1;
	
	
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_INSURANCE_ID")
	private CarsInsurance carsInsurance;
	
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_SERVICE_ID")
	private CarsServices carsServices;
	
	
	@ManyToOne
	@JoinColumn(name="NOTIFICATION_POL_CAR_ID")
	private CarsPolicyCar carsPolicyCar;
	
	@Column(name = "NOTIFICATIONS_STATUS1")
	private java.lang.String notificationsStatus1;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTIFICATION_REPORTED_DATE")
	private java.util.Date notificationReportedDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTIFICATION_STATUS_DATE")
	private java.util.Date notificationStatusDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "NOTIFICATION_MAT_CHANGE_DATE")
	private java.util.Date notificationMatChangeDate;
	
	
	@Column(name = "NOTIFICATION_MAT_CHANGE_USER")
	private java.lang.String notificationMatChangeUser;
	
	
	public CarsNotification() {
		this.notificationId = UUID.randomUUID().toString();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}