package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.DuplicateHostDbm;
import org.codelibs.fess.es.config.cbean.DuplicateHostCB;
import org.codelibs.fess.es.config.cbean.cq.DuplicateHostCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsDuplicateHostCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsDuplicateHostCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsDuplicateHostCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public DuplicateHostDbm asDBMeta() {
        return DuplicateHostDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "duplicate_host";
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
    public DuplicateHostCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsDuplicateHostCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (DuplicateHostCB) this;
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
    public BsDuplicateHostCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsDuplicateHostCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsDuplicateHostCQ createLocalCQ() {
        return new DuplicateHostCQ();
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
        public void columnDuplicateHostName() {
            doColumn("duplicateHostName");
        }
        public void columnRegularName() {
            doColumn("regularName");
        }
        public void columnSortOrder() {
            doColumn("sortOrder");
        }
        public void columnUpdatedBy() {
            doColumn("updatedBy");
        }
        public void columnUpdatedTime() {
            doColumn("updatedTime");
        }
    }
}
