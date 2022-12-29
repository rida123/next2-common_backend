package net.claims.express.next2.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Entity
@Table(name = "CARS_CAR_BRAND")
public class CarBrand extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 5681313477629793233L;
 
    @Column(name = "CAR_BRAND_CODE", nullable = false, length = 5)
    private String carBrandCode;
    
    @Column(name = "CAR_BRAND_DESCRIPTION", length = 20)
    private String carBrandDescription;
    
    @Id
    @Column(name = "CAR_BRAND_ID", nullable = false, length = 36)
    private String carBrandId;
    // @Column(name = "LOG_COMPUTER", length = 50)
    
    @OneToMany(mappedBy = "carBrand", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarTrademark> carTrademarkList;
    
    @Transient
    private String carBrandString;

    public CarBrand() {
        carBrandId = UUID.randomUUID().toString();
    }
    
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
	

}