package net.claims.express.next2.entities;

import lombok.Data;
import net.claims.express.next2.views.InsuranceLov;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_INSURANCE")
public class CarsInsurance extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -5789904783559717705L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "INSURANCE_CODE", nullable = false,insertable = false , updatable = false)
    private InsuranceLov insuranceCodeLov;
 
    @Column(name = "INSURANCE_CODE", nullable = false)
    private String insuranceCode;

    @Column(name = "INSURANCE_DENY_PAY", length = 50)
    private String insuranceDenyPay;
    @Column(name = "INSURANCE_DESC", length = 75)
    private String insuranceDesc;
    @Id
    @Column(name = "INSURANCE_ID", nullable = false, length = 36)
    private String insuranceId;


   /* @OneToMany(mappedBy = "insurance", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsNotification> notificationInsuranceList;
    
    @OneToMany(mappedBy = "insurance", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsPreRiskSurvey> preRiskSurveyList;
    
    @OneToMany(mappedBy = "insurance", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsBranch> branchList;
    
    @OneToMany(mappedBy = "insurance", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsSurveyorPlace> surveyorPlaceList;*/

    public CarsInsurance() {
        insuranceId = UUID.randomUUID().toString();
    }
}