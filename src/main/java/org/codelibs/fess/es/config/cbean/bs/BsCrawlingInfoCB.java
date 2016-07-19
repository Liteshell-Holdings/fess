package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.CrawlingInfoDbm;
import org.codelibs.fess.es.config.cbean.CrawlingInfoCB;
import org.codelibs.fess.es.config.cbean.cq.CrawlingInfoCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsCrawlingInfoCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsCrawlingInfoCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsCrawlingInfoCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public CrawlingInfoDbm asDBMeta() {
        return CrawlingInfoDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "crawling_info";
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
    public CrawlingInfoCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsCrawlingInfoCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (CrawlingInfoCB) this;
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
    public BsCrawlingInfoCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsCrawlingInfoCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsCrawlingInfoCQ createLocalCQ() {
        return new CrawlingInfoCQ();
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

        public void columnCreatedTime() {
            doColumn("createdTime");
        }
        public void columnExpiredTime() {
            doColumn("expiredTime");
        }
        public void columnName() {
            doColumn("name");
        }
        public void columnSessionId() {
            doColumn("sessionId");
        }
    }
}
