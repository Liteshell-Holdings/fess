package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.RoleTypeDbm;
import org.codelibs.fess.es.config.cbean.RoleTypeCB;
import org.codelibs.fess.es.config.cbean.cq.RoleTypeCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsRoleTypeCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsRoleTypeCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsRoleTypeCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public RoleTypeDbm asDBMeta() {
        return RoleTypeDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "role_type";
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
    public RoleTypeCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsRoleTypeCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (RoleTypeCB) this;
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
    public BsRoleTypeCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsRoleTypeCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsRoleTypeCQ createLocalCQ() {
        return new RoleTypeCQ();
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
        public void columnName() {
            doColumn("name");
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
        public void columnValue() {
            doColumn("value");
        }
    }
}
