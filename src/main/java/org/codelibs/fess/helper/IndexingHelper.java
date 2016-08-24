/*
 * Copyright 2012-2016 CodeLibs Project and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.codelibs.fess.helper;

import org.codelibs.fess.es.client.FessEsClient;
import org.codelibs.fess.mylasta.direction.FessConfig;
import org.codelibs.fess.util.ComponentUtil;
import org.codelibs.fess.util.DocList;
import org.codelibs.fess.util.MemoryUtil;
import org.elasticsearch.action.count.CountResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class IndexingHelper {
    private static final Logger logger = LoggerFactory.getLogger(IndexingHelper.class);

    public int maxRetryCount = 5;

    public int defaultRowSize = 100;

    public long requestInterval = 500;

    public void sendDocuments(final FessEsClient fessEsClient, final DocList docList) {
        if (docList.isEmpty()) {
            return;
        }
        final long execTime = System.currentTimeMillis();
            logger.info("Sending " + docList.size() + " documents to a server.");
        try {
            synchronized (fessEsClient) {
                deleteOldDocuments(fessEsClient, docList);
                final FessConfig fessConfig = ComponentUtil.getFessConfig();
                fessEsClient.addAll(fessConfig.getIndexDocumentUpdateIndex(), fessConfig.getIndexDocumentType(), docList);
            }
                if (docList.getContentSize() > 0) {
                    logger.info("Sent " + docList.size() + " docs (Doc:{process " + docList.getProcessingTime() + "ms, send "
                            + (System.currentTimeMillis() - execTime) + "ms, size "
                            + MemoryUtil.byteCountToDisplaySize(docList.getContentSize()) + "}, " + MemoryUtil.getMemoryUsageLog() + ")");
                } else {
                    logger.info("Sent " + docList.size() + " docs (Doc:{send " + (System.currentTimeMillis() - execTime) + "ms}, "
                            + MemoryUtil.getMemoryUsageLog() + ")");
            }
        } finally {
            docList.clear();
        }
    }

    // TODO: 8/24/2016 deleting documents
    private void deleteOldDocuments(final FessEsClient fessEsClient, final DocList docList) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        logger.error("VLAD Document deleteOldDocuments");
        final List<String> docIdList = new ArrayList<>();
        for (final Map<String, Object> inputDoc : docList) {
            final Object idValue = inputDoc.get(fessConfig.getIndexFieldId());
            if (idValue == null) {
                continue;
            }

            final Object configIdValue = inputDoc.get(fessConfig.getIndexFieldConfigId());
            if (configIdValue == null) {
                continue;
            }

            final QueryBuilder queryBuilder =
                    QueryBuilders.boolQuery()
                            .must(QueryBuilders.termQuery(fessConfig.getIndexFieldUrl(), inputDoc.get(fessConfig.getIndexFieldUrl())))
                            .filter(QueryBuilders.termQuery(fessConfig.getIndexFieldConfigId(), configIdValue));

            final List<Map<String, Object>> docs =
                    getDocumentListByQuery(fessEsClient, queryBuilder,
                            new String[] { fessConfig.getIndexFieldId(), fessConfig.getIndexFieldDocId() });
            for (final Map<String, Object> doc : docs) {
                final Object oldIdValue = doc.get(fessConfig.getIndexFieldId());
                if (!idValue.equals(oldIdValue) && oldIdValue != null) {
                    final Object oldDocIdValue = doc.get(fessConfig.getIndexFieldDocId());
                    if (oldDocIdValue != null) {

                        logger.error("VLAD Document already found" + oldDocIdValue.toString());
                      //  docIdList.add(oldDocIdValue.toString());
                    }
                }
            }

                logger.debug("VLAD "+ queryBuilder.toString() + " => " + docs);

        }
        if (!docIdList.isEmpty()) {

            logger.debug("VLAD !docIdList.isEmpty() => ");
            fessEsClient.deleteByQuery(fessConfig.getIndexDocumentUpdateIndex(), fessConfig.getIndexDocumentType(),
                    QueryBuilders.idsQuery(fessConfig.getIndexDocumentType()).ids(docIdList.stream().toArray(n -> new String[n])));

        }
    }

    public void deleteDocument(final FessEsClient fessEsClient, final String id) {

        logger.error("VLAD Document deleteDocument" +id);
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        fessEsClient.delete(fessConfig.getIndexDocumentUpdateIndex(), fessConfig.getIndexDocumentType(), id, 0);
    }

    public void deleteDocumentsByDocId(final FessEsClient fessEsClient, final List<String> docIdList) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        logger.error("VLAD Document deleteDocumentsByDocId " +docIdList.stream().count());
        fessEsClient.deleteByQuery(fessConfig.getIndexDocumentUpdateIndex(), fessConfig.getIndexDocumentType(),
                QueryBuilders.idsQuery(fessConfig.getIndexDocumentType()).ids(docIdList.stream().toArray(n -> new String[n])));
    }

    public Map<String, Object> getDocument(final FessEsClient fessEsClient, final String id, final String[] fields) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        return fessEsClient.getDocument(fessConfig.getIndexDocumentSearchIndex(), fessConfig.getIndexDocumentType(), id,
                requestBuilder -> {
                    return true;
                }).orElse(null);
    }

    public List<Map<String, Object>> getDocumentListByPrefixId(final FessEsClient fessEsClient, final String id, final String[] fields) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        final QueryBuilder queryBuilder = QueryBuilders.prefixQuery(fessConfig.getIndexFieldId(), id);
        return getDocumentListByQuery(fessEsClient, queryBuilder, fields);
    }

    public void deleteChildDocument(final FessEsClient fessEsClient, final String id) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        fessEsClient.deleteByQuery(fessConfig.getIndexDocumentUpdateIndex(), fessConfig.getIndexDocumentType(),
                QueryBuilders.termQuery(fessConfig.getIndexFieldParentId(), id));
    }

    public List<Map<String, Object>> getChildDocumentList(final FessEsClient fessEsClient, final String id, final String[] fields) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();
        final QueryBuilder queryBuilder = QueryBuilders.termQuery(fessConfig.getIndexFieldParentId(), id);
        return getDocumentListByQuery(fessEsClient, queryBuilder, fields);
    }

    protected List<Map<String, Object>> getDocumentListByQuery(final FessEsClient fessEsClient, final QueryBuilder queryBuilder,
            final String[] fields) {
        final FessConfig fessConfig = ComponentUtil.getFessConfig();

        final CountResponse countResponse =
                fessEsClient.prepareCount(fessConfig.getIndexDocumentSearchIndex()).setTypes(fessConfig.getIndexDocumentType())
                        .setQuery(queryBuilder).execute().actionGet(fessConfig.getIndexSearchTimeout());
        final long numFound = countResponse.getCount();
        // TODO max threshold

        return fessEsClient.getDocumentList(fessConfig.getIndexDocumentSearchIndex(), fessConfig.getIndexDocumentType(),
                requestBuilder -> {
                    requestBuilder.setQuery(queryBuilder).setSize((int) numFound);
                    if (fields != null) {
                        requestBuilder.addFields(fields);
                    }
                    return true;
                });

    }
}
