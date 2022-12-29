package net.claims.express.next2.entities;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CORE_DOMAIN_VALUE_ML")
public class CoreDomainValueMl  extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1212479549068247145L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, unique = true, length = 6)
    private String lng;

    @Column(length = 128)
    private String description;

    @Column(name = "SYS_ACTIVE_FLAG", nullable = false)
    private Integer sysActiveFlag;

    @ManyToOne
    @JoinColumn(name = "CORE_DOMAIN_VALUE_ID")
    private CoreDomainValue coreDomainValue;
     
    public CoreDomainValueMl() {
    	this.id = UUID.randomUUID().toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Integer getSysActiveFlag() {
        return sysActiveFlag;
    }

    public void setSysActiveFlag(Integer sysActiveFlag) {
        this.sysActiveFlag = sysActiveFlag;
    }

    public CoreDomainValue getCoreDomainValue() {
        return coreDomainValue;
    }

    public void setCoreDomainValue(CoreDomainValue coreDomainValue) {
        this.coreDomainValue = coreDomainValue;
    }
}
