package wu.mydemo.function.okhttp3.builder;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import wu.mydemo.function.okhttp3.utils.OkCallback;
import wu.mydemo.function.okhttp3.utils.OkhttpUtils;

/**
 * Created by zhaokaiqiang on 15/11/24.
 */
public class PostRequestBuilder<T> extends RequestBuilder<T> {
    private static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");
    private Map<String, String> headers;

    public PostRequestBuilder url(String url) {
        this.url = url;
        return this;
    }

    public PostRequestBuilder setParams(Map<String, String> params) {
        this.params = params;
        return this;
    }

    public PostRequestBuilder addParams(String key, Object value) {
        if (params == null) {
            params = new IdentityHashMap<>();
        }
        params.put(key, String.valueOf(value == null ? "" : value));
        return this;
    }

    public PostRequestBuilder addParams(Map<String, Object> map) {
        if (map == null) {
            return this;
        }
        if (params == null) {
            params = new HashMap<>();
        }
        for (String key : map.keySet()) {
            params.put(key, String.valueOf(map.get(key)));
        }
        return this;
    }

    public PostRequestBuilder headers(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public PostRequestBuilder addHeader(String key, String values) {
        if (headers == null) {
            headers = new IdentityHashMap<>();
        }
        headers.put(key, values);
        return this;
    }

    public PostRequestBuilder tag(Object tag) {
        this.tag = tag;
        return this;
    }


    @Override
    public Call enqueue(Callback callback) {

        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        FormBody.Builder encodingBuilder = new FormBody.Builder();
        appendParams(encodingBuilder, params);
        appendHeaders(builder, headers);
        builder.post(encodingBuilder.build());
        Request request = builder.build();
        if (callback instanceof OkCallback) {
            ((OkCallback) callback).onStart();
        }
        Call call = OkhttpUtils.getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }

    public Call enqueueJson(Callback callback) {

        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        builder.post(RequestBody.create(JSON_TYPE, new Gson().toJson(params)));
        Request request = builder.build();
        if (callback instanceof OkCallback) {
            ((OkCallback) callback).onStart();
        }
        Call call = OkhttpUtils.getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }

    public Call enqueueJson(String json, Callback callback) {

        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        builder.post(RequestBody.create(JSON_TYPE, json));
        Request request = builder.build();
        if (callback instanceof OkCallback) {
            ((OkCallback) callback).onStart();
        }
        Call call = OkhttpUtils.getInstance().newCall(request);
        call.enqueue(callback);
        return call;
    }

    @Override
    public Response execute() throws IOException {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        FormBody.Builder encodingBuilder = new FormBody.Builder();
        appendParams(encodingBuilder, params);
        appendHeaders(builder, headers);
        builder.post(encodingBuilder.build());
        Request request = builder.build();

        Call call = OkhttpUtils.getInstance().newCall(request);
        return call.execute();
    }

    public Response executeJson() throws IOException {
        if (TextUtils.isEmpty(url)) {
            throw new IllegalArgumentException("url can not be null !");
        }

        Request.Builder builder = new Request.Builder().url(url);
        if (tag != null) {
            builder.tag(tag);
        }
        builder.post(RequestBody.create(JSON_TYPE, new Gson().toJson(params)));
        Request request = builder.build();

        Call call = OkhttpUtils.getInstance().newCall(request);
        return call.execute();
    }
}
