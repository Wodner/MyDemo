package wu.mydemo.function.okhttp3;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ProgressBar;

import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.okhttp3.Model.Progress;
import wu.mydemo.function.okhttp3.listener.DownloadListener;
import wu.mydemo.function.okhttp3.utils.OkhttpUtils;
import wu.mydemo.utils.SystemTool;

/**
 * 功能： 网络请求
 * 作者： Administrator
 * 日期： 2017/1/10 16:13
 * 邮箱： descriable
 */
public class OkhttpActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks {


    @Bind(R.id.progress)
    ProgressBar progressbar;

    private String fileDir = "";
    private String fileName = "my.apk";
    private static final int RC_EXTERNAL_STORAGE = 123;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_okhttp;

    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void init() {

    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, OkhttpActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.btn_download, R.id.btn_get, R.id.btn_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_download:
                isDownload = true;
                fileDir = SystemTool.getInnerSDCardPath() + "/myCache/";
                Logger.w(fileDir);
//                download();

                storageTask();
                break;
            case R.id.btn_get:
                OkhttpUtils.cancel(this);
                break;
            case R.id.btn_post:
                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Logger.d("权限不允许");
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logger.d("权限允许");
    }


    @AfterPermissionGranted(RC_EXTERNAL_STORAGE)
    public void storageTask() {

        String[] perms = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

        if (EasyPermissions.hasPermissions(mContext, perms)) {
            // Have permission, do the thing!
            download();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_read_storage),
                    RC_EXTERNAL_STORAGE, perms);
        }
    }


    private void download() {
        OkhttpUtils.download("http://dl.bibibaba.cn/CMWGC.apk", new DownloadListener(fileDir, fileName) {
            @Override
            public void onSuccess(File file) {

            }

            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onUIProgress(Progress progress) {
                if (isDownload){
                    progressbar.setMax((int)progress.getTotalBytes());
                    progressbar.setProgress((int)progress.getCurrentBytes());
                }
                Logger.w("文件大小：" + progress.getTotalBytes() + " 当前进度 ：" + progress.getCurrentBytes());
            }
        });
    }


    boolean isDownload = false;
    @Override
    protected void onStop() {
        super.onStop();
        OkhttpUtils.cancel(this);
    }
}
