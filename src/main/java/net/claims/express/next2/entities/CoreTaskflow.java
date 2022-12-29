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
@Table(name = "CORE_TASKFLOW")
public class CoreTaskflow   extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 2188212714448145145L;

    @Id 
    @Column(nullable = false, length = 36)
    private String id;

    @Column(nullable = false, length = 30)
    private String code;
    @Column(nullable = false, length = 100)
    private String description;

    @Column(name = "TASK_URL", nullable = false, length = 256)
    private String taskUrl;

    @Column(name = "HELP_FILE_NAME")
    private String helpFileName;

    @Column(length = 30)
    private String param1;
   
    @Column(length = 30)
    private String param2;
    
    @Column(length = 30)
    private String param3;
    
    @Column(length = 30)
    private String param4;
    
    @Column(length = 30)
    private String param5;
    
    @Column(length = 30)
    private String param6;
    
    @Column(length = 30)
    private String param7;
    
    @Column(length = 30)
    private String param8;
    
    @Column(length = 30)
    private String param9;
    
    @Column(length = 256)
    private String value1;
    
    @Column(length = 256)
    private String value2;
    
    @Column(length = 256)
    private String value3;
    
    @Column(length = 256)
    private String value4;
    
    @Column(length = 256)
    private String value5;
    
    @Column(length = 256)
    private String value6;
    
    @Column(length = 256)
    private String value7;
    
    @Column(length = 256)
    private String value8;
    
    @Column(length = 256)
    private String value9;
    
    @Transient
    private String rowState;
    
    @ManyToOne
    @JoinColumn(name = "CATEGORY_DV")
    private CoreDomainValue categoryCdv;

   /* @OneToMany(mappedBy = "coreTaskflow", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<CoreTaskflowPerm> coreTaskflowPermList;*/
    

    public CoreTaskflow() {
        id=UUID.randomUUID().toString();
    }
}