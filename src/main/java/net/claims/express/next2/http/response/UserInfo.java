package net.claims.express.next2.http.response;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UserInfo {
    private String displayName ;
    private String userName ;
    private String email ;
    private BigDecimal userLimitDoctorFees;
    private BigDecimal userLimitTaxiFees;
    private BigDecimal userLimitSurveyFees;
    private BigDecimal userLimitExceedPercentage;
    private BigDecimal userLimitLawyerFees;
    private String companyId ;
    private String branchId ;
    private BigDecimal recoverLimit;
    private BigDecimal userLimitHospitalFees;
    private BigDecimal paymentLimit;
    private BigDecimal userLimitExpertFees ;
    private int active ;
    private String companyDescription ;
}
