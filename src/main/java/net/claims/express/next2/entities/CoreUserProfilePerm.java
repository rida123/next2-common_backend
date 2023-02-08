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
@Table(name = "CORE_USER_PROFILE_PERM")
public class CoreUserProfilePerm extends BaseEntity implements Serializable {
   
	private static final long serialVersionUID = 3636474349822295798L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

    @ManyToOne
    @JoinColumn(name = "CORE_USER_PROFILE_ID")
    private CoreUserProfile coreUserProfile;

    @ManyToOne
    @JoinColumn(name = "CORE_ROLE_ID")
    private CoreRole coreRole;
    
    @ManyToOne
    @JoinColumn(name = "ASSIGN_REASON_DV")
    private CoreDomainValue assignReasonDv;
  
    @ManyToOne
    @JoinColumn(name = "CLOSE_REASON_DV")
    private CoreDomainValue closeReasonDv;
    
    @Column(name = "ASSIGN_COMMENT", length = 512)
    private String assignComment;
    
    @Column(name = "CLOSE_COMMENT", length = 512)
    private String closeComment;

    @Column(name = "FROM_DATE", nullable = false)
    private LocalDateTime fromDate;
   
    @Column(name = "TO_DATE", nullable = true)
    private LocalDateTime toDate;

   /* @ManyToOne
    @JoinColumn(name = "CORE_USR_PRF_TSK_PRM_ID")
    private CoreProfileTaskflowPerm coreProfileTaskflowPerm;*/

    //todo: wet set it as a string becase we want are not concerned with adf
    @Column(name = "CORE_TASKFLOW_PERM_ID")
    private String coreProfileTaskflowPerm;
     
    public CoreUserProfilePerm() {
        this.id=UUID.randomUUID().toString(); 
    }
  
}
