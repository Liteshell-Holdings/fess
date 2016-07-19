package org.codelibs.fess.es.log.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.log.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.log.bsentity.dbmeta.ClickLogDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsClickLog extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** queryRequestedAt */
    protected LocalDateTime queryRequestedAt;

    /** requestedAt */
    protected LocalDateTime requestedAt;

    /** queryId */
    protected String queryId;

    /** docId */
    protected String docId;

    /** userSessionId */
    protected String userSessionId;

    /** url */
    protected String url;

    /** order */
    protected Integer order;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public ClickLogDbm asDBMeta() {
        return ClickLogDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "click_log";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (queryRequestedAt != null) {
            sourceMap.put("queryRequestedAt", queryRequestedAt);
        }
        if (requestedAt != null) {
            sourceMap.put("requestedAt", requestedAt);
        }
        if (queryId != null) {
            sourceMap.put("queryId", queryId);
        }
        if (docId != null) {
            sourceMap.put("docId", docId);
        }
        if (userSessionId != null) {
            sourceMap.put("userSessionId", userSessionId);
        }
        if (url != null) {
            sourceMap.put("url", url);
        }
        if (order != null) {
            sourceMap.put("order", order);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(queryRequestedAt);
        sb.append(dm).append(requestedAt);
        sb.append(dm).append(queryId);
        sb.append(dm).append(docId);
        sb.append(dm).append(userSessionId);
        sb.append(dm).append(url);
        sb.append(dm).append(order);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public LocalDateTime getQueryRequestedAt() {
        checkSpecifiedProperty("queryRequestedAt");
        return queryRequestedAt;
    }

    public void setQueryRequestedAt(LocalDateTime value) {
        registerModifiedProperty("queryRequestedAt");
        this.queryRequestedAt = value;
    }

    public LocalDateTime getRequestedAt() {
        checkSpecifiedProperty("requestedAt");
        return requestedAt;
    }

    public void setRequestedAt(LocalDateTime value) {
        registerModifiedProperty("requestedAt");
        this.requestedAt = value;
    }

    public String getQueryId() {
        checkSpecifiedProperty("queryId");
        return convertEmptyToNull(queryId);
    }

    public void setQueryId(String value) {
        registerModifiedProperty("queryId");
        this.queryId = value;
    }

    public String getDocId() {
        checkSpecifiedProperty("docId");
        return convertEmptyToNull(docId);
    }

    public void setDocId(String value) {
        registerModifiedProperty("docId");
        this.docId = value;
    }

    public String getUserSessionId() {
        checkSpecifiedProperty("userSessionId");
        return convertEmptyToNull(userSessionId);
    }

    public void setUserSessionId(String value) {
        registerModifiedProperty("userSessionId");
        this.userSessionId = value;
    }

    public String getUrl() {
        checkSpecifiedProperty("url");
        return convertEmptyToNull(url);
    }

    public void setUrl(String value) {
        registerModifiedProperty("url");
        this.url = value;
    }

    public Integer getOrder() {
        checkSpecifiedProperty("order");
        return order;
    }

    public void setOrder(Integer value) {
        registerModifiedProperty("order");
        this.order = value;
    }
}
