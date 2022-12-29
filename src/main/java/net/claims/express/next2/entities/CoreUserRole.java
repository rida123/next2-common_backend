package net.claims.express.next2.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
@Table(name = "CORE_USER_ROLE")
public class CoreUserRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -3103292982880559643L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

/*
    @ManyToOne
    @JoinColumn(name = "CORE_USER_PROFILE_ID")
    private CoreUserProfile coreUserProfile;
*/

    @ManyToOne
    @JoinColumn(name = "CORE_ROLE_ID")
    private CoreRole coreRole;

    @ManyToOne
    @JoinColumn(name = "ASSIGN_REASON_DV",insertable=false,updatable=false)
    private CoreDomainValue assignReasonDv;

    @Column(name = "ASSIGN_REASON_DV")
    private String assignReasonId;

    @ManyToOne
    @JoinColumn(name = "CLOSE_REASON_DV",insertable=false,updatable=false)
    private CoreDomainValue closeReasonDv;
    
    @Column(name = "CLOSE_REASON_DV")
    private String closeReasonId;

    @Column(name = "ASSIGN_COMMENT", length = 512)
    private String assignComment;

    @Column(name = "CLOSE_COMMENT", length = 512)
    private String closeComment;

    @Column(name = "FROM_DATE", nullable = false)
    private LocalDateTime fromDate;
    
    @Column(name = "TO_DATE", nullable = true)
    private LocalDateTime toDate;

    public CoreUserRole() {
       this.id=UUID.randomUUID().toString();
    }
}