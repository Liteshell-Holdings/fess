package org.codelibs.fess.es.config.cbean.bs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codelibs.fess.es.config.allcommon.EsAbstractConditionBean;
import org.codelibs.fess.es.config.bsentity.dbmeta.FileConfigDbm;
import org.codelibs.fess.es.config.cbean.FileConfigCB;
import org.codelibs.fess.es.config.cbean.cq.FileConfigCQ;
import org.codelibs.fess.es.config.cbean.cq.bs.BsFileConfigCQ;
import org.dbflute.cbean.ConditionQuery;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * @author ESFlute (using FreeGen)
 */
public class BsFileConfigCB extends EsAbstractConditionBean {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    protected BsFileConfigCQ _conditionQuery;
    protected HpSpecification _specification;

    // ===================================================================================
    //                                                                             Control
    //                                                                             =======
    @Override
    public FileConfigDbm asDBMeta() {
        return FileConfigDbm.getInstance();
    }

    @Override
    public String asTableDbName() {
        return "file_config";
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
    public FileConfigCB acceptPK(String id) {
        assertObjectNotNull("id", id);
        BsFileConfigCB cb = this;
        cb.query().docMeta().setId_Equal(id);
        return (FileConfigCB) this;
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
    public BsFileConfigCQ query() {
        assertQueryPurpose();
        return doGetConditionQuery();
    }

    protected BsFileConfigCQ doGetConditionQuery() {
        if (_conditionQuery == null) {
            _conditionQuery = createLocalCQ();
        }
        return _conditionQuery;
    }

    protected BsFileConfigCQ createLocalCQ() {
        return new FileConfigCQ();
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

        public void columnAvailable() {
            doColumn("available");
        }
        public void columnBoost() {
            doColumn("boost");
        }
        public void columnConfigParameter() {
            doColumn("configParameter");
        }
        public void columnCreatedBy() {
            doColumn("createdBy");
        }
        public void columnCreatedTime() {
            doColumn("createdTime");
        }
        public void columnDepth() {
            doColumn("depth");
        }
        public void columnExcludedDocPaths() {
            doColumn("excludedDocPaths");
        }
        public void columnExcludedPaths() {
            doColumn("excludedPaths");
        }
        public void columnIncludedDocPaths() {
            doColumn("includedDocPaths");
        }
        public void columnIncludedPaths() {
            doColumn("includedPaths");
        }
        public void columnIntervalTime() {
            doColumn("intervalTime");
        }
        public void columnTimeToLive() {
            doColumn("timeToLive");
        }
        public void columnMaxAccessCount() {
            doColumn("maxAccessCount");
        }
        public void columnName() {
            doColumn("name");
        }
        public void columnNumOfThread() {
            doColumn("numOfThread");
        }
        public void columnPaths() {
            doColumn("paths");
        }
        public void columnPermissions() {
            doColumn("permissions");
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
