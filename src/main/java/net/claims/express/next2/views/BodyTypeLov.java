package net.claims.express.next2.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BODY_TYPE_LOV_VW")
public class BodyTypeLov implements Serializable {
@SuppressWarnings("compatibility:-4390918121548068347")
private static final long serialVersionUID = 368710733654267066L;

@Id
    @Column(nullable = false, length = 128)
    private String code;

    @Column(nullable = false, length = 128)
    private String description;

    public BodyTypeLov() {
    }

    public BodyTypeLov(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof BodyTypeLov)) {
            return false;
        }
        final BodyTypeLov other = (BodyTypeLov) object;
        if (!(code == null ? other.code == null : code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 37;
        int result = 1;
        result = PRIME * result + ((code == null) ? 0 : code.hashCode());
        return result;
    }
}

