package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.FileConfigToLabelDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsFileConfigToLabel extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** fileConfigId */
    protected String fileConfigId;

    /** labelTypeId */
    protected String labelTypeId;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public FileConfigToLabelDbm asDBMeta() {
        return FileConfigToLabelDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "file_config_to_label";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (fileConfigId != null) {
            sourceMap.put("fileConfigId", fileConfigId);
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
        sb.append(dm).append(fileConfigId);
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
    public String getFileConfigId() {
        checkSpecifiedProperty("fileConfigId");
        return convertEmptyToNull(fileConfigId);
    }

    public void setFileConfigId(String value) {
        registerModifiedProperty("fileConfigId");
        this.fileConfigId = value;
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
