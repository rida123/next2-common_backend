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
import javax.persistence.Transient;

import lombok.Data;

@Data
@Entity
@Table(name = "CORE_ROLE")
public class CoreRole extends BaseEntity implements Serializable {
    private static final long serialVersionUID = -7854836778109067961L;

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    @Transient
    private Boolean selected;

  /*  @ManyToOne
    @JoinColumn(name = "CORE_PROFILE_ID", nullable = true)*/
   @Column(name="CORE_PROFILE_ID")
    private String coreProfile;

   /* @OneToMany(mappedBy = "coreRole", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreUserRole> coreUserRoleList;*/

 /*   @OneToMany(mappedBy = "coreRole", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreUserProfilePerm> coreUserProfilePermList;

    @OneToMany(orphanRemoval = true, mappedBy = "coreRole", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreTaskflowPerm> coreTaskflowPermList;*/

    @Column(name = "DESCRIPTION", nullable = false, length = 4000)
    private String description;
    
   

    public CoreRole() {
         this.id=UUID.randomUUID().toString();
    }
}
