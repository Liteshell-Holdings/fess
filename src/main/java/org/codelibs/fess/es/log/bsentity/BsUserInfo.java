package org.codelibs.fess.es.log.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.log.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.log.bsentity.dbmeta.UserInfoDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsUserInfo extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** createdAt */
    protected LocalDateTime createdAt;

    /** updatedAt */
    protected LocalDateTime updatedAt;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public UserInfoDbm asDBMeta() {
        return UserInfoDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "user_info";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (createdAt != null) {
            sourceMap.put("createdAt", createdAt);
        }
        if (updatedAt != null) {
            sourceMap.put("updatedAt", updatedAt);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(createdAt);
        sb.append(dm).append(updatedAt);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public LocalDateTime getCreatedAt() {
        checkSpecifiedProperty("createdAt");
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime value) {
        registerModifiedProperty("createdAt");
        this.createdAt = value;
    }

    public LocalDateTime getUpdatedAt() {
        checkSpecifiedProperty("updatedAt");
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime value) {
        registerModifiedProperty("updatedAt");
        this.updatedAt = value;
    }
}
