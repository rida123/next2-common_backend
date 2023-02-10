package net.claims.express.next2.http.response;

import org.springframework.beans.factory.annotation.Value;

import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

public interface PolicySearchResponse {
    @Value("#{target.AMENDMENT_DESC}")
     String getAmendmentDesc ();

    @Value("#{target.BRANCH_DESC}")
     String getBranchDesc();


    @Value("#{target.BRAND_DESCRIPTION}")
     String getBrandDescription();

    @Value("#{target.BRAND_DESCRIPTION2}")
     String getBrandDescription2();

    @Value("#{target.BRAND_ID}")
     String getBrandId();

    @Value("#{target.BROKER_NAME}")
     String getBrokerName();

    @Value("#{target.CAR_BRAND_DESC}")
     String getCarBrandDesc();


    @Value("#{target.CAR_CHASSIS}")
     String getCarChassis();

    @Value("#{target.CAR_ID}")
     String getCarId();

    @Value("#{target.CAR_OBJECT_NUMBER}")
     BigDecimal getCarObjectNumber();

    @Value("#{target.CAR_PLATE}")
     String getCarPlate();



    @Value("#{target.CAR_TRADEMARK_DESC}")
     String getCarTrademarkDesc();


    @Value("#{target.CAR_YEAR}")
     BigDecimal getCarYear();

    @Value("#{target.CLIENT_NAME}")
     String getClientName();




    @Value("#{target.INSURANCE_DESC}")
     String getInsuranceDesc();

    @Value("#{target.MECHANICAL_TOW_COUNT}")
     String getMechanicalTowCount();

    @Value("#{target.PLATE_NUM}")
     String getPlateNum();

    @Value("#{target.POLICY_AMENDMENT}")
     Integer getPolicyAmendment();



    @Value("#{target.POLICY_BRANCH_ID}")
     String getPolicyBranchId();


    @Value("#{target.POLICY_BROKER_ID}")
     String getPolicyBrokerId();

    @Value("#{target.POLICY_CAR}")
     Integer getPolicyCar();



    @Value("#{target.POLICY_CLIENT_ID}")
     String getPolicyClientId();

    @Value("#{target.POLICY_EFFECTIVE_DATE}")
     Date getPolicyEffectiveDate();


    @Value("#{target.POLICY_EXPIRY_DATE}")
     Date getPolicyExpiryDate();

    @Value("#{target.POLICY_INSURANCE_ID}")
     String getPolicyInsuranceId();

    @Value("#{target.POLICY_NUMBER}")
     String getPolicyNumber();


    @Value("#{target.POLICY_PRODUCTS_ID}")
     String getPolicyProductsId();

    @Value("#{target.POLICY_TYPE}")
     String getPolicyType();


    @Value("#{target.POLICY_TYPE_DESC}")
     String getPolicyTypeDesc();

    @Value("#{target.POLICY_UWY}")
     Integer getPolicyUwy();



    @Value("#{target.PRODUCT_DESC}")
     String getProductDesc();


    @Value("#{target.PRODUCTS_DESCRIPTION}")
     String getProductsDescription();


    @Value("#{target.SHAPE_ID}")
     String getShapeId();


    @Value("#{target.TRADEMARK_DESCRIPTION}")
     String getTrademarkDescription();

    @Value("#{target.TRADEMARK_DESCRIPTION2}")
     String getTrademarkDescription2();


    @Value("#{target.TRADEMARK_ID}")
     String getTrademarkId();


    @Value("#{target.POLICY_POLSERNO}")
     String getPolicyPolserno();


    @Value("#{target.PRODUCTS_CODE}")
     String getProductsCode();


    @Value("#{target.BRANCH_CODE}")
     String getBranchCode();


    @Value("#{target.POLICY_ISSUE_DATE}")
     Date getPolicyIssueDate ();


    @Value("#{target.TOW_ALLOWED}")
     String getTowAllowed();

}
