package net.claims.express.next2.entities;


import lombok.Data;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_CAZA")
public class CarsCaza extends BaseEntity implements Serializable {
	
    private static final long serialVersionUID = 8038934395524587960L;
    
    @Column(name = "CAZA_ARABIC_NAME", length = 500)
    private String cazaArabicName;
    
    @Column(name = "CAZA_DESCRIPTION", length = 75)
    private String cazaDescription;
    
    @Id
    @Column(name = "CAZA_ID", nullable = false, length = 36)
    private String cazaId;
    
    @Column(name = "CAZA_OFFICER", length = 50)
    private String cazaOfficer;
   

//    @OneToMany(mappedBy = "caza", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private List<CarsSurveyorPlace> surveyorCazaList;
//
//    @OneToMany(mappedBy = "caza", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    private List<CarsTown> cazaTownList;
    
    @ManyToOne
    @JoinColumn(name = "CAZA_REGION_ID")
    private CarsRegion region;

    public CarsCaza() {
        cazaId = UUID.randomUUID().toString();
    }
    
    public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}

}