package net.claims.express.next2.views;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "INSURANCE_LOV_VW")
@NoArgsConstructor
public class InsuranceLov implements Serializable {
    private static final long serialVersionUID = 955041935434145270L;
  
    @Id
    @Column(name = "INSURANCE_CODE", nullable = false)
    private BigDecimal insuranceCode;
    @Column(name = "INSURANCE_DESC", length = 75)
    private String insuranceDesc;

    public InsuranceLov(BigDecimal insuranceCode, String insuranceDesc) {
        this.insuranceCode = insuranceCode;
        this.insuranceDesc = insuranceDesc;
    }

    public BigDecimal getInsuranceCode() {
        return insuranceCode;
    }

    public void setInsuranceCode(BigDecimal insuranceCode) {
        this.insuranceCode = insuranceCode;
    }

    public String getInsuranceDesc() {
        return insuranceDesc;
    }

    public void setInsuranceDesc(String insuranceDesc) {
        this.insuranceDesc = insuranceDesc;
    }

}
