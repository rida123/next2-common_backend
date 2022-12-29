package net.claims.express.next2.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
@Entity
@Table(name = "CARS_CAR_TRADEMARK")
public class CarTrademark extends BaseEntity implements Serializable {
	
    private static final long serialVersionUID = -6094213175539542818L;
    
    @Column(name = "CAR_TRADEMARK_CODE", length = 5)
    private String carTrademarkCode;
    
    @Column(name = "CAR_TRADEMARK_DESCRIPTION", length = 40)
    private String carTrademarkDescription;
    
    @Id
    @Column(name = "CAR_TRADEMARK_ID", nullable = false, length = 36)
    private String carTrademarkId;
    
    @Column(name = "CAR_TRADEMARK_QUERY_ONLY", length = 50)
    private String carTrademarkQueryOnly;
    
    @OneToMany(mappedBy = "carTrademark", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsCarShape> carShapeList;
  
    @ManyToOne
    @JoinColumn(name = "CAR_BRAND_ID")
    private CarBrand carBrand;

    @Transient
    private String carTrademarkDescriptionId;

    public CarTrademark() {
        carTrademarkId = UUID.randomUUID().toString();
    }
    
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
