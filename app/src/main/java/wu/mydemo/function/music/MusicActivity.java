package wu.mydemo.function.music;

import android.Manifest;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.app.MyApplication;
import wu.mydemo.function.music.adapter.SongsAdapter;
import wu.mydemo.function.music.model.Audio;
import wu.mydemo.function.music.utils.MediaUtils;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MusicActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks,SongsAdapter.OnRecyclerViewItemClickListener{


    @Bind(R.id.recycle_song)
    RecyclerView recycleSong;

    private MyApplication application;
    private static final String TAG = "SongFragment";
    private static final int RC_READ_EXTERNAL_STORAGE = 123;
    private List<Audio> audioList = new ArrayList<>();
    private SongsAdapter songsAdapter;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_song;
    }


    @Override
    protected void setUpView() {
        songsAdapter = new SongsAdapter(mContext);
        recycleSong.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycleSong.setLayoutManager(layoutManager);
        recycleSong.setItemAnimator(new DefaultItemAnimator());//添加和移除时候才有动画
        songsAdapter.setData(audioList);
        recycleSong.setAdapter(songsAdapter);
        songsAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void init() {
        storageTask();//权限请求
        application = (MyApplication)mContext.getApplication();
    }

    @Override
    public void onItemClick(View v, Audio audio) {
        Toast.makeText(mContext,audio.getTitle(),Toast.LENGTH_LONG).show();
        application.startPlay(audio);
    }

    @AfterPermissionGranted(RC_READ_EXTERNAL_STORAGE)
    public void storageTask() {
        if (EasyPermissions.hasPermissions(mContext, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            // Have permission, do the thing!
            new GetMediaThread().start();
        } else {
            // Ask for one permission
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_read_storage),
                    RC_READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        Logger.d("权限允许");
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Logger.d("权限不允许");
        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
    }



    class GetMediaThread extends Thread {
        @Override
        public void run() {
            super.run();
            audioList = MediaUtils.getAudioList(mContext);
            mHandler.sendEmptyMessage(UPDATE_UI);
        }
    }


    final int UPDATE_UI = 0;
    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case UPDATE_UI:
                    songsAdapter.setData(audioList);
                    break;
            }
        }
    };

}
