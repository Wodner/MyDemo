package wu.mydemo.function.okhttp3.listener;

import android.os.Handler;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import wu.mydemo.function.okhttp3.Model.Progress;
import wu.mydemo.function.okhttp3.handler.ProgressHandler;
import wu.mydemo.function.okhttp3.handler.UIHandler;


public abstract class UploadListener implements ProgressListener, Callback, UIProgressListener {

    private final Handler mHandler = new UIHandler(this);
    private boolean isFirst = true;

    @Override
    public void onFailure(Call call,final IOException e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onFailure(e);
            }
        });
    }

    @Override
    public void onResponse(Call call,final Response response) throws IOException {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(response);
            }
        });
    }

    public abstract void onSuccess(Response response);

    public abstract void onFailure(Exception e);

    @Override
    public void onProgress(Progress progress) {

        if (!isFirst) {
            isFirst = true;
            mHandler.obtainMessage(ProgressHandler.START, progress)
                    .sendToTarget();
        }

        mHandler.obtainMessage(ProgressHandler.UPDATE,
                progress)
                .sendToTarget();

        if (progress.isFinish()) {
            mHandler.obtainMessage(ProgressHandler.FINISH,
                    progress)
                    .sendToTarget();
        }
    }

    public abstract void onUIProgress(Progress progress);

    public void onUIStart() {
    }

    public void onUIFinish() {
    }
}
