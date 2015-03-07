/*
 * Copyright 2009-2015 the CodeLibs Project and the Others.
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

package org.codelibs.fess.ds;

import java.util.Map;

import org.codelibs.solr.lib.SolrGroup;

public interface IndexUpdateCallback {

    boolean store(Map<String, Object> dataMap);

    void setSolrGroup(SolrGroup solrGroup);

    SolrGroup getSolrGroup();

    void setCommitPerCount(long commitPerCount);

    long getDocumentSize();

    long getExecuteTime();

    void commit();

}