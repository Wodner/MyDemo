package wu.mydemo.app;

import android.app.Application;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.orhanobut.logger.Logger;

import wu.mydemo.function.music.model.Audio;
import wu.mydemo.function.music.service.MusicService;

/**
 * 类描述：
 * 作  者：wuwen
 * 时  间：on 2016/11/19 at 18:43
 * 修改备注：
 */

public class MyApplication extends Application implements ServiceConnection,MusicService.OnPlaybackListener{

    private MusicService mMusicService;

    @Override
    public void onCreate() {
        super.onCreate();
        startMusicService ();
        bindMusicService();
    }

    public void startMusicService () {
        Intent it = new Intent (this, MusicService.class);
        startService(it);
    }

    public void stopMainService () {
        Intent it = new Intent(this, MusicService.class);
        stopService(it);
    }

    private void bindMusicService () {
        Intent it = new Intent (this, MusicService.class);
        this.bindService(it, this, Service.BIND_AUTO_CREATE);
    }

    private void unbindMainService () {
        this.unbindService(this);
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder instanceof MusicService.ServiceBinder) {
            MusicService.ServiceBinder binder = (MusicService.ServiceBinder)iBinder;
            mMusicService = binder.getService();
            mMusicService.setOnPlaybackListener(this);
        }
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {}

    @Override
    public void onStateChanged(Audio source, int state) {
        Logger.d("音乐播放状态 ： " + state);
    }

    public void startPlay(Audio audio){
        if(mMusicService!=null){
            mMusicService.startPlay(audio);
        }
    }

    public void stopPlay(){
        if(mMusicService!=null){
            mMusicService.stopPlay();
        }
    }

    public void nextSong(Audio audio){
        if(mMusicService!=null){

        }
    }

    public void lastSong(Audio audio){
        if(mMusicService!=null){

        }
    }


}
