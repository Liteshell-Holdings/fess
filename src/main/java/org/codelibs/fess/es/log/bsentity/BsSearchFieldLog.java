package org.codelibs.fess.es.log.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.log.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.log.bsentity.dbmeta.SearchFieldLogDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsSearchFieldLog extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** name */
    protected String name;

    /** searchLogId */
    protected String searchLogId;

    /** value */
    protected String value;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public SearchFieldLogDbm asDBMeta() {
        return SearchFieldLogDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "search_field_log";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (name != null) {
            sourceMap.put("name", name);
        }
        if (searchLogId != null) {
            sourceMap.put("searchLogId", searchLogId);
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
        sb.append(dm).append(name);
        sb.append(dm).append(searchLogId);
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
    public String getName() {
        checkSpecifiedProperty("name");
        return convertEmptyToNull(name);
    }

    public void setName(String value) {
        registerModifiedProperty("name");
        this.name = value;
    }

    public String getSearchLogId() {
        checkSpecifiedProperty("searchLogId");
        return convertEmptyToNull(searchLogId);
    }

    public void setSearchLogId(String value) {
        registerModifiedProperty("searchLogId");
        this.searchLogId = value;
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
