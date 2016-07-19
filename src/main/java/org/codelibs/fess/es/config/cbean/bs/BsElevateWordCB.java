package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.ElevateWordDbm;
import org.codelibs.fess.es.config.cbean.ElevateWordCB;
import org.codelibs.fess.es.config.cbean.cq.ElevateWordCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsElevateWordCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsElevateWordCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsElevateWordCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public ElevateWordDbm asDBMeta() {
        return ElevateWordDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "elevate_word";
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
    public ElevateWordCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsElevateWordCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (ElevateWordCB) this;
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
    public BsElevateWordCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsElevateWordCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsElevateWordCQ createLocalCQ() {
        return new ElevateWordCQ();
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

        public void columnBoost() {
            doColumn("boost");
        }
        public void columnCreatedBy() {
            doColumn("createdBy");
        }
        public void columnCreatedTime() {
            doColumn("createdTime");
        }
        public void columnReading() {
            doColumn("reading");
        }
        public void columnSuggestWord() {
            doColumn("suggestWord");
        }
        public void columnTargetLabel() {
            doColumn("targetLabel");
        }
        public void columnTargetRole() {
            doColumn("targetRole");
        }
        public void columnPermissions() {
            doColumn("permissions");
        }
        public void columnUpdatedBy() {
            doColumn("updatedBy");
        }
        public void columnUpdatedTime() {
            doColumn("updatedTime");
        }
    }
}
