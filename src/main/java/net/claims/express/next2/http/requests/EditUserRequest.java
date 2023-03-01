package net.claims.express.next2.http.requests;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EditUserRequest {
    private String displayName ;
    private String userName ;
    private String email ;
    private BigDecimal userLimitDoctorFees;
    private BigDecimal userLimitTaxiFees;
    private BigDecimal userLimitSurveyFees;
    private BigDecimal userLimitExceedPercentage;
    private BigDecimal userLimitLawyerFees;
    private int companyId ;
    private int branchId ;
    private BigDecimal recoverLimit;
    private BigDecimal userLimitHospitalFees;
    private BigDecimal paymentLimit;
    private BigDecimal userLimitExpertFees ;
    private String companyDescription ;
}
