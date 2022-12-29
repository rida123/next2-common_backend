package net.claims.express.next2.entities;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;

@Data
@Entity
@Table(name = "CORE_COMPANY")
public class CoreCompany extends BaseEntity implements Serializable {
	
    private static final long serialVersionUID = 9218479471091972349L;
    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Column(name = "LEGAL_NAME", length = 200)
    private String legalName;

    @Column(nullable = false, length = 36)
    private String name;

    @Column(name = "CLIENT_ID", length = 36)
    private String clientId;

    @Column(name = "REGISTER_INFO", length = 200)
    private String registerInfo;

    @Column(name = "ADDRESS_INFO", length = 200)
    private String addressInfo;

    @OneToMany(mappedBy = "coreCompany", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreCompanyProfile> coreCompanyProfileList;
    

    public CoreCompany() {
    	this.id = UUID.randomUUID().toString();
    }
    
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
   
}
