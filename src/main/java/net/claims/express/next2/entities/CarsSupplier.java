package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_SUPPLIER")

public class CarsSupplier extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "SUPPLIER_COUNTRY")
	private String supplierCountry;
	
	
	@Column(name = "SUPPLIER_COMPANY")
	private Integer supplierCompany;
	
	
	@Column(name = "SUPPLIER_INTERM")
	private String supplierInterm;
	
	
	@Column(name = "SUPPLIER_NUM")
	private Integer supplierNum;
	
	
	@Column(name = "SUPPLIER_TITRE")
	private String supplierTitre;
	
	
	@Column(name = "SUPPLIER_PREFIX_FAM")
	private String supplierPrefixFam;
	
	
	@Column(name = "SUPPLIER_FAMILY_NAME")
	private String supplierFamilyName;
	
	
	@Column(name = "SUPPLIER_FIRST_NAME")
	private String supplierFirstName;
	
	
	@Column(name = "SUPPLIER_FATHER_NAME")
	private String supplierFatherName;
	
	
	@Column(name = "SUPPLIER_HOME_BLDG")
	private String supplierHomeBldg;
	
	
	@Column(name = "SUPPLIER_HOME_STREET")
	private String supplierHomeStreet;
	
	
	@Column(name = "SUPPLIER_HOME_COUNTRY")
	private String supplierHomeCountry;
	
	
	@Column(name = "SUPPLIER_HOME_DISTRICT")
	private String supplierHomeDistrict;
	
	
	@Column(name = "SUPPLIER_HOME_SECTOR")
	private String supplierHomeSector;
	
	
	@Column(name = "SUPPLIER_HOME_CITY")
	private String supplierHomeCity;
	
	
	@Column(name = "SUPPLIER_HOME_ZIP_1")
	private String supplierHomeZip1;
	
	
	@Column(name = "SUPPLIER_HOME_PHONE")
	private String supplierHomePhone;
	
	
	@Column(name = "SUPPLIER_BUS_STREET")
	private String supplierBusStreet;
	
	
	@Column(name = "SUPPLIER_BUS_COUNTRY")
	private String supplierBusCountry;
	
	
	@Column(name = "SUPPLIER_BUS_DISTRICT")
	private String supplierBusDistrict;
	
	
	@Column(name = "SUPPLIER_BUS_SECTOR")
	private String supplierBusSector;
	
	
	@Column(name = "SUPPLIER_BUS_CITY")
	private String supplierBusCity;
	
	
	@Column(name = "SUPPLIER_BUS_ZIP_1")
	private String supplierBusZip1;
	
	
	@Column(name = "SUPPLIER_BUS_PHONE")
	private String supplierBusPhone;
	
	
	@Column(name = "SUPPLIER_MOBILE_PHONE")
	private String supplierMobilePhone;
	
	
	@Column(name = "SUPPLIER_FDATEJ")
	private Integer supplierFdatej;
	
	
	@Column(name = "SUPPLIER_TAXE")
	private String supplierTaxe;
	
	
	@Column(name = "SUPPLIER_INACTDJ")
	private Integer supplierInactdj;
	
	
	@Column(name = "SUPPLIER_SRC")
	private String supplierSrc;
	
	
	@Column(name = "SUPPLIER_NCONTACT")
	private String supplierNcontact;
	
	
	@Column(name = "SUPPLIER_FAX")
	private String supplierFax;
	
	
	@Column(name = "SUPPLIER_MECHANIC_LABOR")
	private Integer supplierMechanicLabor;
	
	
	@Column(name = "SUPPLIER_PAINT_LABOR")
	private Integer supplierPaintLabor;


	
	@Column(name = "SUPPLIER_ARABIC_NAME")
	private String supplierArabicName;
	
	
	@Column(name = "SUPPLIER_SMS")
	private String supplierSms;
	
	
	@Column(name = "SUPPLIER_EMAIL")
	private String supplierEmail;
	
	
	@Column(name = "SUPPLIER_INITIAL_COUNT")
	private Integer supplierInitialCount;
	
	
	@Column(name = "SUPPLIER_REG_NUM")
	private String supplierRegNum;
	
	
	@Column(name = "SUPPLIER_TVA_NUM")
	private String supplierTvaNum;
	
	
	@Id
	@Column(name = "SUPPLIER_ID")
	private String supplierId;
	
	
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_HOME_ID")
	private CarsTown supplierHome;
	
	
	@ManyToOne
	@JoinColumn(name="SUPPLIER_BUS_ID")
	private CarsTown supplierBus;
	
	@Column(name = "SUPPLIER_GRADE_ID")
	private String supplierGradeId;
	
	
	@Column(name = "SUPPLIER_SHOW_IN_LIST")
	private String supplierShowInList;
	
	
	@Column(name = "SUPPLIER_NAME")
	private String supplierName;
	
	
	@Column(name = "SUPPLIER_OUT_NETWORK")
	private String supplierOutNetwork;
	
	
	@Column(name = "SUPPLIER_FDATE")
	private LocalDateTime supplierFdate;
	
	
	@Column(name = "SUPPLIER_INACTD")
	private LocalDateTime supplierInactd;
	
	@Column(name = "SUPPLIER_TYPE")
	private String supplierType;
	
	
	@Column(name = "SUPPLIER_INCLUDE_APP")
	private String supplierIncludeApp;
	
	
	@Column(name = "SUPPLIER_CORE_USER_ID")
	private String supplierCoreUserId;
	
	
	@Column(name = "SUPPLIER_LATITUDE")
	private String supplierLatitude;
	
	
	@Column(name = "SUPPLIER_LONGITUDE")
	private String supplierLongitude;
	
	public CarsSupplier() {
		this.supplierId = UUID.randomUUID().toString();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}