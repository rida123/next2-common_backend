package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CORE_RESOURCE_BUNDLE")

public class CoreResourceBundle extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private String id;
	
	public CoreResourceBundle() {
		this.id = UUID.randomUUID().toString();
	}
	
	
	@Column(name = "LOCALE")
	private String locale;
	
	
	@Column(name = "RESOURCE_KEY")
	private String resourceKey;
	
	
	@Column(name = "RESOURCE_VALUE")
	private String resourceValue;
	
	
	@Column(name = "VERSION")
	private Integer version;
	
	
	
	
	

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
		
}