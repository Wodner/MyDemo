package wu.mydemo.function.okhttp3.utils;

import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/10 16:20
 * 邮箱： descriable
 */
public abstract class OkCallback<T>  implements Callback {

    public abstract void onSuccess(int code, T response);
    public abstract void onFailure(Throwable e);

    private OkBaseParser<T> mParser;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public OkCallback(OkBaseParser<T> mParser) {
        if (mParser == null) {
            throw new IllegalArgumentException("Parser can't be null");
        }
        this.mParser = mParser;
    }

    @Override
    public void onResponse(Call call,final Response response) throws IOException {
        final int code = mParser.getCode();
        try {
            final T t = mParser.parseResponse(response);
            if (response.isSuccessful() && t != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onSuccess(code, t);
                    }
                });
            } else {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        onFailure(new Exception(response.toString()));
                    }
                });
            }
        } catch (final Exception e) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    onFailure(e);
                }
            });
        }
    }


    @Override
    public void onFailure(Call call, final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(e);
            }
        });
    }

    public void onStart() {
    }

}
