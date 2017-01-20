package wu.mydemo.function.okhttp3.utils;

import android.content.Context;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import wu.mydemo.function.okhttp3.body.BodyWrapper;
import wu.mydemo.function.okhttp3.builder.GetRequestBuilder;
import wu.mydemo.function.okhttp3.builder.PostRequestBuilder;
import wu.mydemo.function.okhttp3.builder.UploadRequestBuilder;
import wu.mydemo.function.okhttp3.listener.DownloadListener;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/10 16:32
 * 邮箱： descriable
 */
public class OkhttpUtils {


    private static OkHttpClient mHttpClient;

    private static OkHttpClient init() {
        synchronized (OkhttpUtils.class) {
            if (mHttpClient == null) {
                mHttpClient = new OkHttpClient();
            }
        }
        return mHttpClient;
    }

    public static OkHttpClient getInstance() {
        return mHttpClient == null ? init() : mHttpClient;
    }

    public static void setInstance(OkHttpClient okHttpClient) {
        OkhttpUtils.mHttpClient = okHttpClient;
    }

    public static GetRequestBuilder get() {
        return new GetRequestBuilder();
    }

    public static PostRequestBuilder post() {
        return new PostRequestBuilder();
    }


    /**
     * @param url      请求url
     * @param param    请求参数
     * @param tag      请求的tag(对应的Context,方便在结束时取消)
     * @param callback 请求的回调
     */
    public static void postJson(String url, Context tag, String param, OkCallback callback) {
        new PostRequestBuilder().url(url).tag(tag).enqueueJson(param, callback);
    }

    public static void get(String url, Context tag, Map<String, Object> params, OkCallback callback) {
        new GetRequestBuilder().url(url).tag(tag).addParams(params).enqueue(callback);
    }

    public static void post(String url, Context tag, Map<String, Object> params, OkCallback callback) {
        new PostRequestBuilder().url(url).tag(tag).addParams(params).enqueue(callback);
    }

    public static Call download(String url, DownloadListener downloadListener) {
        Request request = new Request.Builder().url(url).build();
        Call call = BodyWrapper.addProgressResponseListener(getInstance(), downloadListener).newCall(request);
        call.enqueue(downloadListener);
        return call;
    }

    /**
     * default time out is 30 min
     */
    public static UploadRequestBuilder upload() {
        return new UploadRequestBuilder();
    }

    public static void cancel(Object tag) {
        Dispatcher dispatcher = getInstance().dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

}
