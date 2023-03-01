package net.claims.express.next2.views;



import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@NamedQueries({ @NamedQuery(name = "VehicleView.findAll", query = "select o from VehicleView o") })
@Table(name = "V_VEHICLE")
public class VehicleView implements Serializable {
    private static final long serialVersionUID = -1388046176800784232L;
    @Column(name = "AMENDMENT_DESC", length = 8)
    private String amendmentDesc;
    @Column(name = "BRANCH_DESC", length = 400)
    private String branchDesc;
    @Column(name = "BRAND_DESCRIPTION", length = 20)
    private String brandDescription;
    @Column(name = "BRAND_DESCRIPTION2", length = 20)
    private String brandDescription2;
    @Column(name = "BRAND_ID", length = 36)
    private String brandId;
    @Column(name = "BROKER_NAME", length = 500)
    private String brokerName;
    @Column(name = "CAR_BRAND_DESC", length = 300)
    private String carBrandDesc;
    @Column(name = "CAR_CHASSIS", length = 100)
    private String carChassis;

    @Id
    @Column(name = "CAR_ID", nullable = false, length = 36)
    private String carId;

    @Column(name = "CAR_OBJECT_NUMBER", nullable = false)
    private BigDecimal carObjectNumber;
    @Column(name = "CAR_PLATE", length = 50)
    private String carPlate;
    @Column(name = "CAR_TRADEMARK_DESC", length = 300)
    private String carTrademarkDesc;
    @Column(name = "CAR_YEAR")
    private BigDecimal carYear;
    @Column(name = "CLIENT_NAME", length = 1303)
    private String clientName;
    @Column(name = "INSURANCE_DESC", length = 75)
    private String insuranceDesc;
    @Column(name = "MECHANICAL_TOW_COUNT")
    private String mechanicalTowCount;
    @Column(name = "PLATE_NUM", length = 200)
    private String plateNum;
    @Column(name = "POLICY_AMENDMENT", nullable = false)
    private Integer policyAmendment;
    @Column(name = "POLICY_BRANCH_ID", length = 36)
    private String policyBranchId;
    @Column(name = "POLICY_BROKER_ID", length = 36)
    private String policyBrokerId;
    @Column(name = "POLICY_CAR", nullable = false)
    private Integer policyCar;
    @Column(name = "POLICY_CLIENT_ID", length = 36)
    private String policyClientId;
    @Temporal(TemporalType.DATE)
    @Column(name = "POLICY_EFFECTIVE_DATE")
    private Date policyEffectiveDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "POLICY_EXPIRY_DATE")
    private Date policyExpiryDate;
    @Column(name = "POLICY_INSURANCE_ID", length = 36)
    private String policyInsuranceId;
    @Column(name = "POLICY_NUMBER", nullable = false, length = 500)
    private String policyNumber;
    @Column(name = "POLICY_PRODUCTS_ID", length = 36)
    private String policyProductsId;
    @Column(name = "POLICY_TYPE", length = 600)
    private String policyType;
    @Column(name = "POLICY_TYPE_DESC", length = 128)
    private String policyTypeDesc;
    @Column(name = "POLICY_UWY")
    private Integer policyUwy;
    @Column(name = "PRODUCT_DESC", length = 901)
    private String productDesc;
    @Column(name = "PRODUCTS_DESCRIPTION", length = 600)
    private String productsDescription;
    @Column(name = "SHAPE_ID", length = 36)
    private String shapeId;
    @Column(name = "TRADEMARK_DESCRIPTION", length = 40)
    private String trademarkDescription;
    @Column(name = "TRADEMARK_DESCRIPTION2", length = 40)
    private String trademarkDescription2;
    @Column(name = "TRADEMARK_ID", length = 36)
    private String trademarkId;
    
    @Column(name = "POLICY_POLSERNO", length = 100)
    private String policyPolserno;
    @Column(name = "PRODUCTS_CODE", length = 100)
    private String productsCode;
    @Column(name = "BRANCH_CODE", length = 100)
    private String branchCode;
    @Temporal(TemporalType.DATE)
    @Column(name = "POLICY_ISSUE_DATE")
    private Date policyIssueDate;

    @Column(name = "TOW_ALLOWED")
    private String towAllowed;

    @Transient
    private String front = "y";

    public VehicleView() {
    }

    public VehicleView(String amendmentDesc, String branchDesc, String brandDescription, String brandDescription2,
                       String brandId, String brokerName, String carBrandDesc, String carChassis, String carId,
                       BigDecimal carObjectNumber, String carPlate, String carTrademarkDesc, BigDecimal carYear,
                       String clientName, String insuranceDesc, String mechanicalTowCount, String plateNum,
                       Integer policyAmendment, String policyBranchId, String policyBrokerId, Integer policyCar,
                       String policyClientId, Date policyEffectiveDate, Date policyExpiryDate, String policyInsuranceId,
                       String policyNumber, String policyProductsId, String policyType, String policyTypeDesc,
                       Integer policyUwy, String productDesc, String productsDescription, String shapeId,
                       String trademarkDescription, String trademarkDescription2, String trademarkId) {
        this.amendmentDesc = amendmentDesc;
        this.branchDesc = branchDesc;
        this.brandDescription = brandDescription;
        this.brandDescription2 = brandDescription2;
        this.brandId = brandId;
        this.brokerName = brokerName;
        this.carBrandDesc = carBrandDesc;
        this.carChassis = carChassis;
        this.carId = carId;
        this.carObjectNumber = carObjectNumber;
        this.carPlate = carPlate;
        this.carTrademarkDesc = carTrademarkDesc;
        this.carYear = carYear;
        this.clientName = clientName;
        this.insuranceDesc = insuranceDesc;
        this.mechanicalTowCount = mechanicalTowCount;
        this.plateNum = plateNum;
        this.policyAmendment = policyAmendment;
        this.policyBranchId = policyBranchId;
        this.policyBrokerId = policyBrokerId;
        this.policyCar = policyCar;
        this.policyClientId = policyClientId;
        this.policyEffectiveDate = policyEffectiveDate;
        this.policyExpiryDate = policyExpiryDate;
        this.policyInsuranceId = policyInsuranceId;
        this.policyNumber = policyNumber;
        this.policyProductsId = policyProductsId;
        this.policyType = policyType;
        this.policyTypeDesc = policyTypeDesc;
        this.policyUwy = policyUwy;
        this.productDesc = productDesc;
        this.productsDescription = productsDescription;
        this.shapeId = shapeId;
        this.trademarkDescription = trademarkDescription;
        this.trademarkDescription2 = trademarkDescription2;
        this.trademarkId = trademarkId;
    }

    public String getAmendmentDesc() {
        return amendmentDesc;
    }

    public void setAmendmentDesc(String amendmentDesc) {
        this.amendmentDesc = amendmentDesc;
    }

    public String getBranchDesc() {
        return branchDesc;
    }

    public void setBranchDesc(String branchDesc) {
        this.branchDesc = branchDesc;
    }

    public String getBrandDescription() {
        return brandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        this.brandDescription = brandDescription;
    }

    public String getBrandDescription2() {
        return brandDescription2;
    }

    public void setBrandDescription2(String brandDescription2) {
        this.brandDescription2 = brandDescription2;
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrokerName() {
        return brokerName;
    }

    public void setBrokerName(String brokerName) {
        this.brokerName = brokerName;
    }

    public String getCarBrandDesc() {
        return carBrandDesc;
    }

    public void setCarBrandDesc(String carBrandDesc) {
        this.carBrandDesc = carBrandDesc;
    }

    public String getCarChassis() {
        return carChassis;
    }

    public void setCarChassis(String carChassis) {
        this.carChassis = carChassis;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public BigDecimal getCarObjectNumber() {
        return carObjectNumber;
    }

    public void setCarObjectNumber(BigDecimal carObjectNumber) {
        this.carObjectNumber = carObjectNumber;
    }

    public String getCarPlate() {
        return carPlate;
    }

    public void setCarPlate(String carPlate) {
        this.carPlate = carPlate;
    }

    public String getCarTrademarkDesc() {
        return carTrademarkDesc;
    }

    public void setCarTrademarkDesc(String carTrademarkDesc) {
        this.carTrademarkDesc = carTrademarkDesc;
    }

    public BigDecimal getCarYear() {
        return carYear;
    }

    public void setCarYear(BigDecimal carYear) {
        this.carYear = carYear;
    }

    public String getClientName() {
        return clientName;
    }

    public String getClientShortName() {
        if (clientName.isEmpty()) {
            return "";
        }
        return clientName.substring(0, Math.min(clientName.length(), 26));
    }

    public String getBrokerShortName() {
        if (brokerName.isEmpty()) {
            return "";
        }
        return brokerName.substring(0, Math.min(brokerName.length(), 17));
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getInsuranceDesc() {
        return insuranceDesc;
    }

    public void setInsuranceDesc(String insuranceDesc) {
        this.insuranceDesc = insuranceDesc;
    }

    public String getMechanicalTowCount() {
        return mechanicalTowCount;
    }

    public void setMechanicalTowCount(String mechanicalTowCount) {
        this.mechanicalTowCount = mechanicalTowCount;
    }

    public String getPlateNum() {
        return plateNum;
    }

    public void setPlateNum(String plateNum) {
        this.plateNum = plateNum;
    }

    public Integer getPolicyAmendment() {
        return policyAmendment;
    }

    public void setPolicyAmendment(Integer policyAmendment) {
        this.policyAmendment = policyAmendment;
    }

    public String getPolicyBranchId() {
        return policyBranchId;
    }

    public void setPolicyBranchId(String policyBranchId) {
        this.policyBranchId = policyBranchId;
    }

    public String getPolicyBrokerId() {
        return policyBrokerId;
    }

    public void setPolicyBrokerId(String policyBrokerId) {
        this.policyBrokerId = policyBrokerId;
    }

    public Integer getPolicyCar() {
        return policyCar;
    }

    public void setPolicyCar(Integer policyCar) {
        this.policyCar = policyCar;
    }

    public String getPolicyClientId() {
        return policyClientId;
    }

    public void setPolicyClientId(String policyClientId) {
        this.policyClientId = policyClientId;
    }

    public Date getPolicyEffectiveDate() {
        return policyEffectiveDate;
    }

    public void setPolicyEffectiveDate(Date policyEffectiveDate) {
        this.policyEffectiveDate = policyEffectiveDate;
    }

    public Date getPolicyExpiryDate() {
        return policyExpiryDate;
    }

    public void setPolicyExpiryDate(Date policyExpiryDate) {
        this.policyExpiryDate = policyExpiryDate;
    }

    public String getPolicyInsuranceId() {
        return policyInsuranceId;
    }

    public void setPolicyInsuranceId(String policyInsuranceId) {
        this.policyInsuranceId = policyInsuranceId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyProductsId() {
        return policyProductsId;
    }

    public void setPolicyProductsId(String policyProductsId) {
        this.policyProductsId = policyProductsId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getPolicyTypeDesc() {
        return policyTypeDesc;
    }

    public void setPolicyTypeDesc(String policyTypeDesc) {
        this.policyTypeDesc = policyTypeDesc;
    }

    public Integer getPolicyUwy() {
        return policyUwy;
    }

    public void setPolicyUwy(Integer policyUwy) {
        this.policyUwy = policyUwy;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getProductsDescription() {
        return productsDescription;
    }

    public void setProductsDescription(String productsDescription) {
        this.productsDescription = productsDescription;
    }

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public String getTrademarkDescription() {
        return trademarkDescription;
    }

    public void setTrademarkDescription(String trademarkDescription) {
        this.trademarkDescription = trademarkDescription;
    }

    public String getTrademarkDescription2() {
        return trademarkDescription2;
    }

    public void setTrademarkDescription2(String trademarkDescription2) {
        this.trademarkDescription2 = trademarkDescription2;
    }

    public String getTrademarkId() {
        return trademarkId;
    }

    public void setTrademarkId(String trademarkId) {
        this.trademarkId = trademarkId;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getFront() {
        return front;
    }

    public void setPolicyPolserno(String policyPolserno) {
        this.policyPolserno = policyPolserno;
    }

    public String getPolicyPolserno() {
        return policyPolserno;
    }

    public void setProductsCode(String productsCode) {
        this.productsCode = productsCode;
    }

    public String getProductsCode() {
        return productsCode;
    }

    public void setPolicyIssueDate(Date policyIssueDate) {
        this.policyIssueDate = policyIssueDate;
    }

    public Date getPolicyIssueDate() {
        return policyIssueDate;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setTowAllowed(String towAllowed) {
        this.towAllowed = towAllowed;
    }

    public String getTowAllowed() {
        return towAllowed;
    }
}
