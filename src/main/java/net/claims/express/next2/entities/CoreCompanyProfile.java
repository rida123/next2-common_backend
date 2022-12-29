package net.claims.express.next2.entities;


import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import lombok.Data;
import org.springframework.data.repository.cdi.Eager;

@Data
@Entity
@Table(name = "CORE_COMPANY_PROFILE")
public class CoreCompanyProfile extends BaseEntity implements Serializable {
   
	private static final long serialVersionUID = 4228154711619542485L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "CORE_COMPANY_ID")
    private CoreCompany coreCompany;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CORE_PROFILE_ID")
    private CoreProfile coreProfile;


    public CoreCompanyProfile() {
        id=UUID.randomUUID().toString();
    }
    
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
