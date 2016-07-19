package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.LabelToRoleDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsLabelToRole extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** labelTypeId */
    protected String labelTypeId;

    /** roleTypeId */
    protected String roleTypeId;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public LabelToRoleDbm asDBMeta() {
        return LabelToRoleDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "label_to_role";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (labelTypeId != null) {
            sourceMap.put("labelTypeId", labelTypeId);
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
        sb.append(dm).append(labelTypeId);
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
    public String getLabelTypeId() {
        checkSpecifiedProperty("labelTypeId");
        return convertEmptyToNull(labelTypeId);
    }

    public void setLabelTypeId(String value) {
        registerModifiedProperty("labelTypeId");
        this.labelTypeId = value;
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
