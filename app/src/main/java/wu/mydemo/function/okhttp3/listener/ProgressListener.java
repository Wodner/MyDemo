package wu.mydemo.function.okhttp3.listener;

import wu.mydemo.function.okhttp3.Model.Progress;

public interface ProgressListener {
    void onProgress(Progress progress);
}