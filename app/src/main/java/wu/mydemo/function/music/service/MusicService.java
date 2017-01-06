package wu.mydemo.function.music.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

import wu.mydemo.function.music.model.Audio;
import wu.mydemo.function.music.model.MusicState;

/**
 * 类描述：
 * 作  者：wuwen
 * 时  间：on 2016/11/19 at 18:43
 * 修改备注：
 */

public class MusicService extends Service implements MediaPlayer.OnErrorListener,MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener{

    private MediaPlayer mPlayer;
    private int mCurrentState;
    private OnPlaybackListener onPlaybackListener;
    private Audio mCurrentAudio;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ServiceBinder(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
        //return super.onStartCommand(intent, flags, startId);
    }


    public static class ServiceBinder extends Binder {
        private MusicService mService = null;

        public ServiceBinder(MusicService service) {
            mService = service;
        }
        public MusicService getService () {
            return mService;
        }
    }


    //这个方法用来初始化我们的MediaPlayer
    private void init () {
        if (mPlayer == null) {
            mPlayer = new MediaPlayer();
            changeState(MusicState.IDLE);
        } else {
            if (mCurrentState == MusicState.IDLE || mCurrentState == MusicState.INITIALIZED || mCurrentState == MusicState.PREPARED ||
                    mCurrentState == MusicState.STARTED || mCurrentState == MusicState.PAUSED || mCurrentState == MusicState.STOPPED ||
                    mCurrentState == MusicState.COMPLETED || mCurrentState == MusicState.ERROR) {
                mPlayer.reset();
                changeState(MusicState.IDLE);        //注意状态更改的代码
            }

        }
        mPlayer.setOnErrorListener(this);       //MainService 要实现这三个接口
        mPlayer.setOnPreparedListener(this);
        mPlayer.setOnCompletionListener(this);
    }


    public void startPlay(Audio audio){
        this.mCurrentAudio = audio;
        with(audio);
    }

    /**
     * 停止播放
     */
    public void stopPlay(){
        if (mPlayer!=null){
            mPlayer.stop();
        }
    }


    /**
     * 播放音乐状态改变
     * @param state
     */
    private void changeState (int state) {
        mCurrentState = state;
        if (onPlaybackListener != null) {
            onPlaybackListener.onStateChanged(mCurrentAudio, mCurrentState);
        }
    }
    /*这里采用了setOnPlaybackListener的方法，如果有需要，也可以用一个List去保存一个Listener集合，只要在适当的时候进行释放，例如在Service的onDestroy方法中，去把这个List清空掉*/
    public void setOnPlaybackListener (OnPlaybackListener listener) {
        onPlaybackListener = listener;
    }

    public interface OnPlaybackListener {
        void onStateChanged(Audio source, int state);
    }


    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        changeState(MusicState.PREPARED);
        mPlayer.start();
        changeState(MusicState.STARTED);
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        changeState(MusicState.COMPLETED);
    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        changeState(MusicState.ERROR);
        return false;
    }

    /**
     * prepare
     * @param audio
     */
    public void with (Audio audio) {
        init();
        try {
            if (mCurrentState == MusicState.IDLE) {
                mPlayer.setDataSource(this, Uri.parse(audio.getPath()) );    //Valid Sates IDLE
            }
            changeState(MusicState.INITIALIZED);
            if (mCurrentState != MusicState.ERROR) {
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);      //Invalid States ERROR
            }
            if (mCurrentState == MusicState.INITIALIZED || mCurrentState == MusicState.STOPPED) {
                mPlayer.prepareAsync();//Valid Sates{Initialized, Stopped}
                changeState(MusicState.PREPARING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
