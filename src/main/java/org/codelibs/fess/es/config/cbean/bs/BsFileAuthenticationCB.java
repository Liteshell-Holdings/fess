package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.FileAuthenticationDbm;
import org.codelibs.fess.es.config.cbean.FileAuthenticationCB;
import org.codelibs.fess.es.config.cbean.cq.FileAuthenticationCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsFileAuthenticationCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsFileAuthenticationCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsFileAuthenticationCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public FileAuthenticationDbm asDBMeta() {
        return FileAuthenticationDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "file_authentication";
    }

    @Override
    public boolean hasSpecifiedColumn() {
        return _specification != null;
    }

    @Override
    public ConditionQuery localCQ() {
        return doGetConditionQuery();
    }

    // ===================================================================================
    //                                                                         Primary Key
    //                                                                         ===========
    public FileAuthenticationCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsFileAuthenticationCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (FileAuthenticationCB) this;
    }

    @Override
    public void acceptPrimaryKeyMap(Map<String, ? extends Object> primaryKeyMap) {
        acceptPK((String)primaryKeyMap.get("_id"));
    }

    // ===================================================================================
    //                                                                               Build
    //                                                                               =====

    @Override
    public SearchRequestBuilder build(SearchRequestBuilder builder) {
        if (_conditionQuery != null) {
            QueryBuilder queryBuilder = _conditionQuery.getQuery();
            if (queryBuilder != null) {
                builder.setQuery(queryBuilder);
            }
            _conditionQuery.getFieldSortBuilderList().forEach(sort -> {
                builder.addSort(sort);
            });
        }

        if (_specification != null) {
            builder.setFetchSource(_specification.columnList.toArray(new String[_specification.columnList.size()]), null);
        }

        return builder;
    }

    // ===================================================================================
    //                                                                               Query
    //                                                                               =====
    public BsFileAuthenticationCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsFileAuthenticationCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsFileAuthenticationCQ createLocalCQ() {
        return new FileAuthenticationCQ();
    }

    // ===================================================================================
    //                                                                             Specify
    //                                                                             =======
    public HpSpecification specify() {
        assertSpecifyPurpose();
        if (_specification == null) {
            _specification = new HpSpecification();
        }
        return _specification;
    }

    protected void assertQueryPurpose() {
    }

    protected void assertSpecifyPurpose() {
    }

    public static class HpSpecification {
        private List<String> columnList = new ArrayList<>();

        private void doColumn(String name) {
            columnList.add(name);
        }

        public void columnId() {
            doColumn("_id");
        }

        public void columnCreatedBy() {
            doColumn("createdBy");
        }
        public void columnCreatedTime() {
            doColumn("createdTime");
        }
        public void columnFileConfigId() {
            doColumn("fileConfigId");
        }
        public void columnHostname() {
            doColumn("hostname");
        }
        public void columnParameters() {
            doColumn("parameters");
        }
        public void columnPassword() {
            doColumn("password");
        }
        public void columnPort() {
            doColumn("port");
        }
        public void columnProtocolScheme() {
            doColumn("protocolScheme");
        }
        public void columnUpdatedBy() {
            doColumn("updatedBy");
        }
        public void columnUpdatedTime() {
            doColumn("updatedTime");
        }
        public void columnUsername() {
            doColumn("username");
        }
    }
}
