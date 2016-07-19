package org.codelibs.fess.es.user.bsentity;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

import org.codelibs.fess.es.user.allcommon.EsAbstractEntity;
import org.codelibs.fess.es.user.bsentity.dbmeta.GroupDbm;

/**
 * ${table.comment}
 * @author ESFlute (using FreeGen)
 */
public class BsGroup extends EsAbstractEntity {

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

    /** gidNumber */
    protected Long gidNumber;

    // [Referrers] *comment only

    // ===================================================================================
    //                                                                             DB Meta
    //                                                                             =======
    @Override
    public GroupDbm asDBMeta() {
        return GroupDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "group";
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
        if (gidNumber != null) {
            sourceMap.put("gidNumber", gidNumber);
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
        sb.append(dm).append(gidNumber);
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

    public Long getGidNumber() {
        checkSpecifiedProperty("gidNumber");
        return gidNumber;
    }

    public void setGidNumber(Long value) {
        registerModifiedProperty("gidNumber");
        this.gidNumber = value;
    }
}
