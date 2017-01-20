package wu.mydemo.function.okhttp3.listener;


import wu.mydemo.function.okhttp3.Model.Progress;

/**
 * Created by zhaokaiqiang on 15/11/23.
 */
public interface UIProgressListener {

    void onUIProgress(Progress progress);

    void onUIStart();

    void onUIFinish();
}
