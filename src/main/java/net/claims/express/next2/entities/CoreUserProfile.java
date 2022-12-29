package net.claims.express.next2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "core_user_profile")
@Data
public class CoreUserProfile extends  BaseEntity {

    @Id
    private String id;

    @Column(name = "core_user_id")
    private String coreUserId;

    @Column(name = "core_company_profile_id")
    private String coreCompanyProfileId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "core_user_profile_perm",
            joinColumns = @JoinColumn(name = "core_user_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "core_role_id")
    )
    private Set<CoreRole> userRoles;

    public CoreUserProfile() {
//        this.id = UUID.randomUUID().toString();
    }

}
