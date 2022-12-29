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

import lombok.Data;

@Data
@Entity
@Table(name = "CORE_DOMAIN_VALUE")
public class CoreDomainValue   extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -1510709639133809428L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, length = 128)
    private String code;

    @Column(nullable = false, length = 128)
    private String description;

    @Column(name = "SYS_ACTIVE_FLAG", nullable = false)
    private Integer sysActiveFlag=new Integer(0);
    
    @Column(length = 128)
    private String val1;
  
    @Column(length = 128)
    private String val10;
    
    @Column(length = 128)
    private String val2;
    
    @Column(length = 128)
    private String val3;
    
    @Column(length = 128)
    private String val4;
    
    @Column(length = 128)
    private String val5;
    
    @Column(length = 128)
    private String val6;
    
    @Column(length = 128)
    private String val7;
    
    @Column(length = 128)
    private String val8;
    
    @Column(length = 128)
    private String val9;
    
    
    @ManyToOne
    @JoinColumn(name = "CORE_DOMAIN_ID")
    private CoreDomain coreDomain;

    @OneToMany(mappedBy = "coreDomainValue", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreDomainValueMl> coreDomainValueMlList;

    public CoreDomainValue() { 
    	this.id = UUID.randomUUID().toString();
    }    
}
