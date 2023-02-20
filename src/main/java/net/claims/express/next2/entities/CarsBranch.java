package net.claims.express.next2.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
@Entity
@Table(name = "CARS_BRANCH")
public class CarsBranch extends BaseEntity implements Serializable {


    @Column(name = "BRANCH_ADR_1", length = 40)
    private String branchAdr1;
 
    @Column(name = "BRANCH_ADR_2", length = 40)
    private String branchAdr2;
    
    @Column(name = "BRANCH_CODE", nullable = false)
    private BigDecimal branchCode;
    
    @Column(name = "BRANCH_DES", length = 400)
    private String branchDes;
    
    @Column(name = "BRANCH_DES_ARA", length = 400)
    private String branchDesAra;
    
    @Id
    @Column(name = "BRANCH_ID", nullable = false, length = 36)
    private String branchId;
  
  /*  @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsFilesSent> carsFilesSentList;*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRANCH_INSURANCE_ID")
    private CarsInsurance carsInsurance;

  /*  @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsLossCar> carsLossCarList;*/

 /*   @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsLossTowing> carsLossTowingList;
    @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsInvoiceRecp> carsInvoiceRecpList;
    @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsPolicy> carsPolicyList;
    @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsResPayInfo> carsResPayInfoList;
    @OneToMany(orphanRemoval = true, mappedBy = "carsBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsResPay> carsResPayList;
    @OneToMany(orphanRemoval = true, mappedBy = "carBranch", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsBlackList> carsBlackListList;
 */
    public CarsBranch() {
        branchId = UUID.randomUUID().toString();
    }
    
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}
