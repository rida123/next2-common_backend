package net.claims.express.next2.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class BaseEntity {

//    private String contextCurrentUser;

    @Column(name="SYS_CREATED_BY")
    private String sysCreatedBy;

    @Column(name="SYS_CREATED_DATE")
    private LocalDateTime sysCreatedDate;

    @Column(name="SYS_UPDATED_BY")
    private String sysUpdatedBy;

    @Column(name="SYS_UPDATED_DATE")
    private LocalDateTime sysUpdatedDate;

    @Column(name="SYS_VERSION_NUMBER")
    private Long sysVersionNumber;

    //default constructor
    public BaseEntity() { }
}
