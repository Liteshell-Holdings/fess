package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.LabelTypeDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsLabelType extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** createdBy */
    protected String createdBy;

    /** createdTime */
    protected Long createdTime;

    /** excludedPaths */
    protected String excludedPaths;

    /** includedPaths */
    protected String includedPaths;

    /** name */
    protected String name;

    /** permissions */
    protected String[] permissions;

    /** sortOrder */
    protected Integer sortOrder;

    /** updatedBy */
    protected String updatedBy;

    /** updatedTime */
    protected Long updatedTime;

    /** value */
    protected String value;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public LabelTypeDbm asDBMeta() {
        return LabelTypeDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "label_type";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (createdBy != null) {
            sourceMap.put("createdBy", createdBy);
        }
        if (createdTime != null) {
            sourceMap.put("createdTime", createdTime);
        }
        if (excludedPaths != null) {
            sourceMap.put("excludedPaths", excludedPaths);
        }
        if (includedPaths != null) {
            sourceMap.put("includedPaths", includedPaths);
        }
        if (name != null) {
            sourceMap.put("name", name);
        }
        if (permissions != null) {
            sourceMap.put("permissions", permissions);
        }
        if (sortOrder != null) {
            sourceMap.put("sortOrder", sortOrder);
        }
        if (updatedBy != null) {
            sourceMap.put("updatedBy", updatedBy);
        }
        if (updatedTime != null) {
            sourceMap.put("updatedTime", updatedTime);
        }
        if (value != null) {
            sourceMap.put("value", value);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(createdBy);
        sb.append(dm).append(createdTime);
        sb.append(dm).append(excludedPaths);
        sb.append(dm).append(includedPaths);
        sb.append(dm).append(name);
        sb.append(dm).append(permissions);
        sb.append(dm).append(sortOrder);
        sb.append(dm).append(updatedBy);
        sb.append(dm).append(updatedTime);
        sb.append(dm).append(value);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getCreatedBy() {
        checkSpecifiedProperty("createdBy");
        return convertEmptyToNull(createdBy);
    }

    public void setCreatedBy(String value) {
        registerModifiedProperty("createdBy");
        this.createdBy = value;
    }

    public Long getCreatedTime() {
        checkSpecifiedProperty("createdTime");
        return createdTime;
    }

    public void setCreatedTime(Long value) {
        registerModifiedProperty("createdTime");
        this.createdTime = value;
    }

    public String getExcludedPaths() {
        checkSpecifiedProperty("excludedPaths");
        return convertEmptyToNull(excludedPaths);
    }

    public void setExcludedPaths(String value) {
        registerModifiedProperty("excludedPaths");
        this.excludedPaths = value;
    }

    public String getIncludedPaths() {
        checkSpecifiedProperty("includedPaths");
        return convertEmptyToNull(includedPaths);
    }

    public void setIncludedPaths(String value) {
        registerModifiedProperty("includedPaths");
        this.includedPaths = value;
    }

    public String getName() {
        checkSpecifiedProperty("name");
        return convertEmptyToNull(name);
    }

    public void setName(String value) {
        registerModifiedProperty("name");
        this.name = value;
    }

    public String[] getPermissions() {
        checkSpecifiedProperty("permissions");
        return permissions;
    }

    public void setPermissions(String[] value) {
        registerModifiedProperty("permissions");
        this.permissions = value;
    }

    public Integer getSortOrder() {
        checkSpecifiedProperty("sortOrder");
        return sortOrder;
    }

    public void setSortOrder(Integer value) {
        registerModifiedProperty("sortOrder");
        this.sortOrder = value;
    }

    public String getUpdatedBy() {
        checkSpecifiedProperty("updatedBy");
        return convertEmptyToNull(updatedBy);
    }

    public void setUpdatedBy(String value) {
        registerModifiedProperty("updatedBy");
        this.updatedBy = value;
    }

    public Long getUpdatedTime() {
        checkSpecifiedProperty("updatedTime");
        return updatedTime;
    }

    public void setUpdatedTime(Long value) {
        registerModifiedProperty("updatedTime");
        this.updatedTime = value;
    }

    public String getValue() {
        checkSpecifiedProperty("value");
        return convertEmptyToNull(value);
    }

    public void setValue(String value) {
        registerModifiedProperty("value");
        this.value = value;
    }
}
