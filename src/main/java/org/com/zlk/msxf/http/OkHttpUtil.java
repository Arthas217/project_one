package org.com.zlk.msxf.http;

import cn.hutool.core.collection.CollectionUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 会游泳的蚂蚁
 * @date 2024/3/23 14:16
 */
@Slf4j
public class OkHttpUtil {

    @Data
    public static class ClientConfig {
        private int madIdleConnections = 0;
        private long keepAliveDuration = 10;
        private long connectionTimeout = 15;
        private long readTimeout = 10;
        private long writeTimeout = 10;
        private TimeUnit timeUnit = TimeUnit.SECONDS;
    }


    public static OkHttpUtil defaultClient() {
        return DEFAULT;
    }


    public static OkHttpUtil defaultProxyClient(String proxyUrl, Integer proxyPort) {
        if (DEFAULT_PROXY == null) {
            return DEFAULT_PROXY = new OkHttpUtil(new ClientConfig(), proxyUrl, proxyPort);
        }
        return DEFAULT_PROXY;
    }


    public static OkHttpUtil build(ClientConfig clientConfig) {
        if (clientConfig == null) {
            return DEFAULT;
        }
        return new OkHttpUtil(clientConfig);
    }


    /**
     * GET 请求  param和head是map类型
     *
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public HttpResult doGet(String url, Map<String, String> params, Map<String, String> headers) {
        long start = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder(url);
        if (params != null && params.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : params.keySet()) {
                if (firstFlag) {
                    stringBuilder.append("?").append(key).append("=").append(params.get(key));
                    firstFlag = false;
                } else {
                    stringBuilder.append("&").append(key).append("=").append(params.get(key));
                }
            }
        }

        Request.Builder builder = new Request.Builder();
        if (!CollectionUtil.isEmpty(headers)) {
            headers.forEach(builder::addHeader);
        }

        Request request = builder.url(stringBuilder.toString()).build();
        log.info("okhttp util do GET request url={}, cost:{}", stringBuilder, System.currentTimeMillis() - start);
        return execute(request);
    }


    /**
     * POST请求  参数Map
     *
     * @param url
     * @param params
     * @return
     */
    public HttpResult doPostForm(String url, Map<String, String> params) {
        long start = System.currentTimeMillis();
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && params.keySet().size() > 0) {
            for (String key : params.keySet()) {
                builder.add(key, params.get(key));
            }
        }
        Request request = new Request.Builder().url(url).post(builder.build()).build();
        log.info("okhttp util do Post request url={}, cost:{}", url, System.currentTimeMillis() - start);
        return execute(request);
    }


    public HttpResult doPostUploadFile(String url, Map<String, Object> params) {
        long start = System.currentTimeMillis();

        Headers.Builder headBuilder = new Headers.Builder();
        headBuilder.add("Content-Type", "multipart/form-data;");
        Headers headers = headBuilder.build();

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        if (params != null) {
            for (String key : params.keySet()) {
                if (params.get(key) instanceof File) {
                    File file = (File) params.get(key);
                    builder.addFormDataPart(key, file.getName(), RequestBody.create(MediaType.parse(MEDIA_TYPE_MULTIPART_FROM), file));
                } else {
                    builder.addFormDataPart(key, String.valueOf(params.get(key)));
                }
            }
        }
        MultipartBody multipartBody = builder.build();

        Request request = new Request.Builder().url(url).headers(headers).post(multipartBody).build();
        log.info("okhttp util do Post from request url={}, cost:{}", url, System.currentTimeMillis() - start);
        return execute(request);
    }


    public HttpResult doPostJson(String url, Map<String, ?> params, Map<String, String> headerMap) {
        return doPostJson(url, null, JsonUtil.toJsonString(params), headerMap);
    }


    public HttpResult doDelete(String url, Map<String, String> headerMap) {
        long start = System.currentTimeMillis();
        Headers.Builder headerBuilder = new Headers.Builder();
        if (!CollectionUtil.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> {
                if (v != null) {
                    headerBuilder.add(k, v);
                }
            });
        }

        Headers headers = headerBuilder.build();
        Request request = new Request.Builder().url(url).headers(headers).delete().build();
        log.info("okhttp util do DELETE  request url={}, head={}, cost:{}", url, JsonUtil.toJsonString(headerMap), System.currentTimeMillis() - start);
        return execute(request);
    }


    public HttpResult doPatch(String url, Object requestObject, Map<String, String> headerMap) {
        long start = System.currentTimeMillis();
        Headers.Builder headerBuilder = new Headers.Builder();
        if (!CollectionUtil.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> {
                if (v != null) {
                    headerBuilder.add(k, v);
                }
            });
        }
        String bodyContent = requestObject instanceof String ? (String) requestObject : JsonUtil.toJsonString(requestObject);
        RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_TYPE_JSON), bodyContent);

        Headers headers = headerBuilder.build();
        Request request = new Request.Builder().url(url).headers(headers).patch(requestBody).build();
        log.info("okhttp util do PATCH  request url={}, head={}, cost:{}", url, JsonUtil.toJsonString(headerMap), System.currentTimeMillis() - start);
        return execute(request);
    }


    private static final String MEDIA_TYPE_JSON = "application/json; charset=utf-8";

    private static final String MEDIA_TYPE_MULTIPART_FROM = "multipart/form-data;";

    private static final OkHttpUtil DEFAULT = build(new ClientConfig());

    private static OkHttpUtil DEFAULT_PROXY;

    private final OkHttpClient okHttpClient;

    private OkHttpUtil(ClientConfig clientConfig) {
        okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectionPool(new ConnectionPool(clientConfig.madIdleConnections, clientConfig.getKeepAliveDuration(), clientConfig.getTimeUnit()))
                .connectTimeout(clientConfig.getConnectionTimeout(), clientConfig.getTimeUnit())
                .readTimeout(clientConfig.getReadTimeout(), clientConfig.getTimeUnit())
                .writeTimeout(clientConfig.getWriteTimeout(), clientConfig.getTimeUnit())
                .hostnameVerifier((hostname, session) -> true)
                .build();
    }

    private OkHttpUtil(ClientConfig clientConfig, String proxyUrl, Integer proxyPort) {
        //代理无效、未配置
        if (StringUtil.isBlank(proxyUrl) || proxyPort == null || proxyPort <= 0) {
            okHttpClient = new OkHttpClient.Builder()
                    .retryOnConnectionFailure(false)
                    .connectionPool(new ConnectionPool(clientConfig.madIdleConnections, clientConfig.getKeepAliveDuration(), clientConfig.getTimeUnit()))
                    .connectTimeout(clientConfig.getConnectionTimeout(), clientConfig.getTimeUnit())
                    .readTimeout(clientConfig.getReadTimeout(), clientConfig.getTimeUnit())
                    .writeTimeout(clientConfig.getWriteTimeout(), clientConfig.getTimeUnit())
                    .hostnameVerifier((hostname, session) -> true)
                    .build();
            log.warn("okhttp proxy invalid 地址:{} 端口:{}", proxyUrl, proxyPort);
            return;
        }

        okHttpClient = new OkHttpClient.Builder()
                .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyUrl, proxyPort)))
                .retryOnConnectionFailure(false)
                .connectionPool(new ConnectionPool(clientConfig.madIdleConnections, clientConfig.getKeepAliveDuration(), clientConfig.getTimeUnit()))
                .connectTimeout(clientConfig.getConnectionTimeout(), clientConfig.getTimeUnit())
                .readTimeout(clientConfig.getReadTimeout(), clientConfig.getTimeUnit())
                .writeTimeout(clientConfig.getWriteTimeout(), clientConfig.getTimeUnit())
                .hostnameVerifier((hostname, session) -> true)
                .build();
        log.warn("okhttp proxy  地址:{} 端口:{}", proxyUrl, proxyPort);
    }

    private HttpResult execute(Request request) {
        try (Response response = okHttpClient.newCall(request).execute()) {
            assert response != null;
            ResponseBody body = response.body();
            return new HttpResult(response.code(), null != body ? body.string() : null);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new HttpSendException(e.getMessage(), e, null);
        }
    }


    private HttpResult doPostJson(String url, Map<String, ?> formParams, String bodyContent, Map<String, String> headerMap) {
        long start = System.currentTimeMillis();
        Headers.Builder headBuilder = new Headers.Builder();
        if (!CollectionUtil.isEmpty(headerMap)) {
            headerMap.forEach((k, v) -> {
                if (v != null) {
                    headBuilder.add(k, v);
                }
            });
        }

        StringBuilder stringBuilder = new StringBuilder(url);
        if (formParams != null && formParams.keySet().size() > 0) {
            boolean firstFlag = true;
            for (String key : formParams.keySet()) {
                if (firstFlag) {
                    stringBuilder.append("?").append(key).append("=").append(formParams.get(key));
                    firstFlag = false;
                } else {
                    stringBuilder.append("&").append(key).append("=").append(formParams.get(key));
                }
            }
        }

        Headers headers = headBuilder.build();
        RequestBody requestBody = RequestBody.create(MediaType.parse(MEDIA_TYPE_JSON), bodyContent);
        Request request = new Request.Builder().url(stringBuilder.toString()).headers(headers).post(requestBody).build();
        log.info("okhttp util do Post json request url={}, head={}, params={},cost:{}", url, JsonUtil.toJsonString(headerMap), bodyContent, System.currentTimeMillis() - start);
        return execute(request);
    }
}
