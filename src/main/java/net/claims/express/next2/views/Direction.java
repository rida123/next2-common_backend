package net.claims.express.next2.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Data
@Entity
@Table(name="PART_DIRECTION_LOV_VW")
@EntityListeners(AuditingEntityListener.class)
public class Direction implements Cloneable {
	@Id
	@Column(name="CODE")
	private String code;

	@Column(name="DESCRIPTION")
	private String description;




}