package net.claims.express.next2.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name="POLICY_TYPE_LOV_VW")
@EntityListeners(AuditingEntityListener.class)
public class PolicyType {

	@Id
	@Column(name="CODE")
	private String code;

	@Column(name="DESCRIPTION")
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
