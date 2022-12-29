package net.claims.express.next2.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CARS_CAR_SHAPE")
public class CarsCarShape extends BaseEntity implements Serializable {
    
	private static final long serialVersionUID = -3021063941864431866L;
    
	@Column(name = "CAR_SHAPE_CODE", length = 5)
    private String carShapeCode;
    
	@Column(name = "CAR_SHAPE_DESCRIPTION", length = 40)
    private String carShapeDescription;
    
	@Id
    @Column(name = "CAR_SHAPE_ID", nullable = false, length = 36)
    private String carShapeId;
    
    @ManyToOne
    @JoinColumn(name = "CAR_TRADEMARK_ID")
    private CarTrademark carTrademark;
    
    /*@OneToMany(mappedBy = "carShape", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsPreRiskSurvey> preRiskCarShapeList;
    
    @OneToMany(mappedBy = "carShape", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsPolicyCar> policyCarList;
    
    @OneToMany(mappedBy = "carShape", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CarsLossCar> lossCarShapeList;*/

    public CarsCarShape() {
        carShapeId = UUID.randomUUID().toString();
    }

}