package org.codelibs.fess.es.log.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.log.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.log.bsentity.dbmeta.ClickLogDbm;
import org.codelibs.fess.es.log.cbean.ClickLogCB;
import org.codelibs.fess.es.log.cbean.cq.ClickLogCQ;
import org.codelibs.fess.es.log.cbean.cq.bs.BsClickLogCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsClickLogCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsClickLogCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public ClickLogDbm asDBMeta() {
        return ClickLogDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "click_log";
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
    public ClickLogCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsClickLogCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (ClickLogCB) this;
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
    public BsClickLogCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsClickLogCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsClickLogCQ createLocalCQ() {
        return new ClickLogCQ();
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

        public void columnQueryRequestedAt() {
            doColumn("queryRequestedAt");
        }
        public void columnRequestedAt() {
            doColumn("requestedAt");
        }
        public void columnQueryId() {
            doColumn("queryId");
        }
        public void columnDocId() {
            doColumn("docId");
        }
        public void columnUserSessionId() {
            doColumn("userSessionId");
        }
        public void columnUrl() {
            doColumn("url");
        }
        public void columnOrder() {
            doColumn("order");
        }
    }
}
