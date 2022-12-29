package net.claims.express.next2.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

//@Entity
//@Table(name = "CORE_USER_PROFILE")
@Data
public class UserEntity {

//    @Id
    private String id;

//    @Column(name = "FULL_FLAG", nullable = false)
    private Integer fullFlag;

//    @Transient
    private String username;

//    @Transient
    private String password;


 /*   @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_authorities",
            joinColumns = @JoinColumn(name="userid"),
            inverseJoinColumns   = @JoinColumn(name = "authorityid")
    )*/
    private Set<Next2Authority> authorities;

}
