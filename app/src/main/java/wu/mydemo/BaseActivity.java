package wu.mydemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import butterknife.ButterKnife;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * BaseActivity
 * Created by Administrator on 2016/12/21.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private View mRootView;
    private FrameLayout mContent;//内容栏
    protected ProgressDialog mProgressDialog;
    /**
     * 设置布局文件
     */
    @LayoutRes
    protected abstract int setContentViewId();

    /**
     * 初始化View的方法
     */
    protected abstract void setUpView();

    /**
     * 初始化方法
     */
    protected abstract void init();

    public <T extends View> T $(@IdRes int idRes) {
        return (T) findViewById(idRes);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContent = $(R.id.fl_base_content);
        mContext = this;
        MyStatusBarUtil.setStatusColor(this,getResources().getColor(R.color.colro_theme));
        setContent();
    }

    private void setContent() {
        mContent.removeAllViews();
        if(setContentViewId() != 0){
            mRootView = getLayoutInflater().inflate(setContentViewId(), mContent);
            ButterKnife.bind(this, mRootView);
        }else{
            ButterKnife.bind(this);
        }
        setUpView();
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    /**
     * 显示加载框
     * @param msg
     */
    protected void showProgressDialog(String msg) {
        if (mProgressDialog != null && mProgressDialog.isShowing()) return;
        if (mProgressDialog == null) mProgressDialog = new ProgressDialog(mContext);
        if (TextUtils.isEmpty(msg)) msg = "加载中...";
        mProgressDialog.setMessage(msg);
        mProgressDialog.show();
    }

    /**
     * 隐藏加载框
     */
    protected void dismissProgressDialog() {
        if (mProgressDialog == null) return;
        if (mProgressDialog.isShowing()) mProgressDialog.dismiss();
    }

}
