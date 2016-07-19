package org.codelibs.fess.es.config.bsentity;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.BoostDocumentRuleDbm;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsBoostDocumentRule extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** boostExpr */
    protected String boostExpr;

    /** createdBy */
    protected String createdBy;

    /** createdTime */
    protected Long createdTime;

    /** sortOrder */
    protected Integer sortOrder;

    /** updatedBy */
    protected String updatedBy;

    /** updatedTime */
    protected Long updatedTime;

    /** urlExpr */
    protected String urlExpr;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public BoostDocumentRuleDbm asDBMeta() {
        return BoostDocumentRuleDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "boost_document_rule";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (boostExpr != null) {
            sourceMap.put("boostExpr", boostExpr);
        }
        if (createdBy != null) {
            sourceMap.put("createdBy", createdBy);
        }
        if (createdTime != null) {
            sourceMap.put("createdTime", createdTime);
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
        if (urlExpr != null) {
            sourceMap.put("urlExpr", urlExpr);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(boostExpr);
        sb.append(dm).append(createdBy);
        sb.append(dm).append(createdTime);
        sb.append(dm).append(sortOrder);
        sb.append(dm).append(updatedBy);
        sb.append(dm).append(updatedTime);
        sb.append(dm).append(urlExpr);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getBoostExpr() {
        checkSpecifiedProperty("boostExpr");
        return convertEmptyToNull(boostExpr);
    }

    public void setBoostExpr(String value) {
        registerModifiedProperty("boostExpr");
        this.boostExpr = value;
    }

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

    public String getUrlExpr() {
        checkSpecifiedProperty("urlExpr");
        return convertEmptyToNull(urlExpr);
    }

    public void setUrlExpr(String value) {
        registerModifiedProperty("urlExpr");
        this.urlExpr = value;
    }
}
