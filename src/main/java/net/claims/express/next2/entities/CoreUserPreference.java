package net.claims.express.next2.entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "CORE_USER_PREFERENCE")
public class CoreUserPreference extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 3324625587333744231L;

    @Id
    @Column(nullable = false, length = 36)
    private String id;

    /*  NotNull
    Size(min = 3,max=30)
    Pattern(regexp = "[a-z-A-Z]*", message = "Name has invalid characters")
    */
    @NotNull
    @Size(min = 2, max = 50, message = "{name_size_params}" )
    @Column(name = "DISPLAY_NAME", nullable = false, length = 256)
    private String displayName;

    @Column(length = 12)
    private String locale;

    @Column(name = "SKIN_ID", length = 30)
    private String skinId;

    /* Pattern(regexp="[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)", message = "{email_format}")*/
    @NotNull(message = "Email Address is compulsory")
    @Pattern(regexp =
             "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9]" +
             "(?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9]" + "(?:[a-z0-9-]*[a-z0-9])?", message = "{email_format}")
    @Column(name = "USER_EMAIL", nullable = false, length = 100)
    private String userEmail;

    @Column(name = "PWD_KEEP_DAYS")
    private Integer pwdKeepDays = 0;

    @Column(name = "PWD_LAST_UPDATED_DATE")
    private LocalDateTime pwdLastUpdatedDate;

    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CORE_COMPANY_ID")
    private CoreCompany coreCompany;

//    @ManyToOne
//    @JoinColumn(name = "CORE_Taskflow_ID")
//    private CoreTaskflow coreTaskflow;
//
//    @OneToOne(orphanRemoval = true)
//    @JoinColumn(name = "CORE_DOCUMENT_FILE_ID")
//    private CoreDocumentFile coreDocumentFile;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "CORE_USER_ID")
    private CoreUser coreUser;
    
    @Transient
    private String companyName;
    

    public CoreUserPreference() {
    	this.id = UUID.randomUUID().toString();
    }

}