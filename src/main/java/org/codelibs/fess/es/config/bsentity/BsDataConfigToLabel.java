package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.DataConfigToLabelDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsDataConfigToLabel extends EsAbstractEntity {

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

    /** labelTypeId */
    protected String labelTypeId;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public DataConfigToLabelDbm asDBMeta() {
        return DataConfigToLabelDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "data_config_to_label";
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
        if (labelTypeId != null) {
            sourceMap.put("labelTypeId", labelTypeId);
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
        sb.append(dm).append(labelTypeId);
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

    public String getLabelTypeId() {
        checkSpecifiedProperty("labelTypeId");
        return convertEmptyToNull(labelTypeId);
    }

    public void setLabelTypeId(String value) {
        registerModifiedProperty("labelTypeId");
        this.labelTypeId = value;
    }
}
