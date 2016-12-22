package wu.mydemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.ButterKnife;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * BaseActivity
 * Created by Administrator on 2016/12/21.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Activity mContext;
    private View mRootView;
    private FrameLayout mTitleBar;//标题栏
    private FrameLayout mContent;//内容栏
    protected ProgressDialog mProgressDialog;
//    private RelativeLayout rootLayout;
    private Toolbar mToolbar;
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
//        rootLayout = (RelativeLayout)findViewById(R.id.root_layout);
        mTitleBar = $(R.id.fl_base_titleBar);
        mContent = $(R.id.fl_base_content);
        mContext = this;
        MyStatusBarUtil.setStatusColor(this,getResources().getColor(R.color.colro_theme));
        setTitleBar(R.layout.layout_titlebar_base);
        setContent();
    }


    private void setTitleBar(@LayoutRes int layoutResID) {
        mTitleBar.removeAllViews();
        LayoutInflater.from(this).inflate(layoutResID, mTitleBar);
        showTitleBar();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolbar();
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

    /**
     * 初始化 Toolbar
     */
    private void initToolbar(){
        setHomeNav(true);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    /**
     * 设置toolbar 标题
     * @param tiele
     */
    @SuppressLint("NewApi")
    protected  void setTitleText( CharSequence tiele){
        if(mToolbar!=null)
            mToolbar.setTitle(tiele);
    }

    /**
     * 设置toolbar 副标题
     * @param tiele
     */
    @SuppressLint("NewApi")
    protected  void setSubtitle( CharSequence tiele){
        if(mToolbar!=null)
            mToolbar.setSubtitle(tiele);
    }

    /**
     * 设置toolbar 图标
     * @param idRes
     */
    @SuppressLint("NewApi")
    protected void setLogo(int idRes){
        if(mToolbar!=null)
            mToolbar.setLogo(idRes);
    }
    /**
     * 设置toolbar 返回图标
     * @param idRes
     */
    protected  void  setNavIco(int idRes){
        if(mToolbar!=null)
            mToolbar.setNavigationIcon(idRes);
    }

    /**
     * 设置toolbar 背景颜色
     * @param color
     */
    protected void setToolbarBackgroundColor(int color){
        if(mToolbar!=null)
            mToolbar.setBackgroundColor(color);
    }

    /**
     * toolbar 文字颜色
     * @param color
     */
    protected void setTitleTextColor(int color){
        if(mToolbar!=null)
            mToolbar.setTitleTextColor(color);
    }

    protected void setHomeNav(boolean b){
        if(mToolbar!=null){
            setSupportActionBar(mToolbar);
            //设置toolbar后调用setDisplayHomeAsUpEnabled
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

//    /**
//     * 设置最底层的背景图 ， 背景沉浸可以使用
//     * @param idRes
//     */
//    protected void setRootLayoutBackgroundRes(int idRes){
//        rootLayout.setBackgroundResource(idRes);
//    }
//    /**
//     * 设置最底层的背景图 ， 背景沉浸可以使用
//     * @param color
//     */
//    protected void setRootLayoutBackgroundColor(int color){
//        rootLayout.setBackgroundResource(color);
//    }

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

    /*显示标题栏*/
    protected void showTitleBar() {
        if (mTitleBar == null) return;
        mTitleBar.setVisibility(View.VISIBLE);
    }

    /*隐藏标题栏*/
    protected void hideTitleBar() {
        if (mTitleBar == null) return;
        mTitleBar.setVisibility(View.GONE);
    }

}
