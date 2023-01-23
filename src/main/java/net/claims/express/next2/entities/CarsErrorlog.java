package net.claims.express.next2.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "CARS_ERRORLOG")
public class CarsErrorlog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = -9071237385891570427L;


    @Id
    @Column(name = "ERRORLOG_ID", nullable = false, length = 360)
    private String errorlogId;

    @Column(name = "ERRORLOG_KEY_VALUE", length = 4000)
    private String errorlogKeyValue;

    @Column(name = "ERRORLOG_PROG_NAME", length = 50)
    private String errorlogProgName;

    @Column(name = "ERRORLOG_TABLE_NAME", length = 100)
    private String errorlogTableName;

    @Column(name = "ERRORLOG_INSURANCE_ID", length = 36)
    private String errorlogInsuranceId;

    @Column(name = "ERRORLOG_ERROR_CODE")
    private String errorlogErrorCode;

    @Column(name = "ERRORLOG_ERROR_MSG", length = 4000)
    private String errorlogErrorMsg;

    @Column(name = "ERRORLOG_ERROR_TYPE", length = 500)
    private String errorlogType;

    public CarsErrorlog() {
        errorlogId = UUID.randomUUID().toString();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }


}
