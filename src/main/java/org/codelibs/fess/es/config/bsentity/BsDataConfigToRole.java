package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.DataConfigToRoleDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsDataConfigToRole extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** dataConfigId */
    protected String dataConfigId;

    /** roleTypeId */
    protected String roleTypeId;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public DataConfigToRoleDbm asDBMeta() {
        return DataConfigToRoleDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "data_config_to_role";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (dataConfigId != null) {
            sourceMap.put("dataConfigId", dataConfigId);
        }
        if (roleTypeId != null) {
            sourceMap.put("roleTypeId", roleTypeId);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(dataConfigId);
        sb.append(dm).append(roleTypeId);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getDataConfigId() {
        checkSpecifiedProperty("dataConfigId");
        return convertEmptyToNull(dataConfigId);
    }

    public void setDataConfigId(String value) {
        registerModifiedProperty("dataConfigId");
        this.dataConfigId = value;
    }

    public String getRoleTypeId() {
        checkSpecifiedProperty("roleTypeId");
        return convertEmptyToNull(roleTypeId);
    }

    public void setRoleTypeId(String value) {
        registerModifiedProperty("roleTypeId");
        this.roleTypeId = value;
    }
}
