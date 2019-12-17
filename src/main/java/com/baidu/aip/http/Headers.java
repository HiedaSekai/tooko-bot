/*
 * Copyright 2017 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.baidu.aip.http;

/**
 * Common BOS HTTP header values used throughout the BCE BOS Java client.
 */
public interface Headers {

    /*
     * Standard HTTP Headers
     */

    String AUTHORIZATION = "Authorization";

    String CACHE_CONTROL = "Cache-Control";

    String CONTENT_DISPOSITION = "Content-Disposition";

    String CONTENT_ENCODING = "Content-Encoding";

    String CONTENT_LENGTH = "Content-Length";

    String CONTENT_MD5 = "Content-MD5";

    String CONTENT_RANGE = "Content-Range";

    String CONTENT_TYPE = "Content-Type";

    String DATE = "Date";

    String ETAG = "ETag";

    String EXPIRES = "Expires";

    String HOST = "Host";

    String LAST_MODIFIED = "Last-Modified";

    String RANGE = "Range";

    String SERVER = "Server";

    String TRANSFER_ENCODING = "Transfer-Encoding";

    String USER_AGENT = "User-Agent";


    /*
     * BCE Common HTTP Headers
     */

    String BCE_ACL = "x-bce-acl";

    String BCE_CONTENT_SHA256 = "x-bce-content-sha256";

    String BCE_COPY_METADATA_DIRECTIVE = "x-bce-metadata-directive";

    String BCE_COPY_SOURCE = "x-bce-copy-source";

    String BCE_COPY_SOURCE_IF_MATCH = "x-bce-copy-source-if-match";

    String BCE_DATE = "x-bce-date";

    String BCE_DEBUG_ID = "x-bce-debug-id";

    String BCE_PREFIX = "x-bce-";

    String BCE_REQUEST_ID = "x-bce-request-id";

    String BCE_SECURITY_TOKEN = "x-bce-security-token";

    String BCE_STORAGE_CLASS = "x-bce-storage-class";

    String BCE_USER_METADATA_PREFIX = "x-bce-meta-";

}
