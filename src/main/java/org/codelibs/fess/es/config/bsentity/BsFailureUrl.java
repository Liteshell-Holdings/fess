package org.codelibs.fess.es.config.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.config.bsentity.dbmeta.FailureUrlDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsFailureUrl extends EsAbstractEntity {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    private static final long serialVersionUID = 1L;
    protected static final Class<?> suppressUnusedImportLocalDateTime = LocalDateTime.class;

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    /** configId */
    protected String configId;

    /** errorCount */
    protected Integer errorCount;

    /** errorLog */
    protected String errorLog;

    /** errorName */
    protected String errorName;

    /** lastAccessTime */
    protected Long lastAccessTime;

    /** threadName */
    protected String threadName;

    /** url */
    protected String url;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public FailureUrlDbm asDBMeta() {
        return FailureUrlDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "failure_url";
    }

    // ===================================================================================
    //                                                                              Source
    //                                                                              ======
    @Override
    public Map<String, Object> toSource() {
        Map<String, Object> sourceMap = new HashMap<>();
        if (configId != null) {
            sourceMap.put("configId", configId);
        }
        if (errorCount != null) {
            sourceMap.put("errorCount", errorCount);
        }
        if (errorLog != null) {
            sourceMap.put("errorLog", errorLog);
        }
        if (errorName != null) {
            sourceMap.put("errorName", errorName);
        }
        if (lastAccessTime != null) {
            sourceMap.put("lastAccessTime", lastAccessTime);
        }
        if (threadName != null) {
            sourceMap.put("threadName", threadName);
        }
        if (url != null) {
            sourceMap.put("url", url);
        }
        return sourceMap;
    }

    // ===================================================================================
    //                                                                      Basic Override
    //                                                                      ==============
    @Override
    protected String doBuildColumnString(String dm) {
        StringBuilder sb = new StringBuilder();
        sb.append(dm).append(configId);
        sb.append(dm).append(errorCount);
        sb.append(dm).append(errorLog);
        sb.append(dm).append(errorName);
        sb.append(dm).append(lastAccessTime);
        sb.append(dm).append(threadName);
        sb.append(dm).append(url);
        if (sb.length() > dm.length()) {
            sb.delete(0, dm.length());
        }
        sb.insert(0, "{").append("}");
        return sb.toString();
    }

    // ===================================================================================
    //                                                                            Accessor
    //                                                                            ========
    public String getConfigId() {
        checkSpecifiedProperty("configId");
        return convertEmptyToNull(configId);
    }

    public void setConfigId(String value) {
        registerModifiedProperty("configId");
        this.configId = value;
    }

    public Integer getErrorCount() {
        checkSpecifiedProperty("errorCount");
        return errorCount;
    }

    public void setErrorCount(Integer value) {
        registerModifiedProperty("errorCount");
        this.errorCount = value;
    }

    public String getErrorLog() {
        checkSpecifiedProperty("errorLog");
        return convertEmptyToNull(errorLog);
    }

    public void setErrorLog(String value) {
        registerModifiedProperty("errorLog");
        this.errorLog = value;
    }

    public String getErrorName() {
        checkSpecifiedProperty("errorName");
        return convertEmptyToNull(errorName);
    }

    public void setErrorName(String value) {
        registerModifiedProperty("errorName");
        this.errorName = value;
    }

    public Long getLastAccessTime() {
        checkSpecifiedProperty("lastAccessTime");
        return lastAccessTime;
    }

    public void setLastAccessTime(Long value) {
        registerModifiedProperty("lastAccessTime");
        this.lastAccessTime = value;
    }

    public String getThreadName() {
        checkSpecifiedProperty("threadName");
        return convertEmptyToNull(threadName);
    }

    public void setThreadName(String value) {
        registerModifiedProperty("threadName");
        this.threadName = value;
    }

    public String getUrl() {
        checkSpecifiedProperty("url");
        return convertEmptyToNull(url);
    }

    public void setUrl(String value) {
        registerModifiedProperty("url");
        this.url = value;
    }
}
