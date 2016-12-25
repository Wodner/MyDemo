package wu.mydemo.function.swipeBack.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import wu.mydemo.BaseActivity;
import wu.mydemo.function.swipeBack.helper.FinishHelper;
import wu.mydemo.function.swipeBack.helper.SwipeBackLayout;


/**
 * 自带右滑返回上一个界面的Activity
 * 使用:直接继承自SwipeBackActivity即可,用在项目中可以让你的BaseActivity集成这个
 * 控制是否支持手势getSwipeBackLayout().setEnableGesture(false);
 */
public class SwipeBackActivity extends BaseActivity implements SwipeBackActivityBase {

    private SwipeBackActivityHelper mHelper;

    @Override
    protected int setContentViewId() {
        return 0;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void init() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        FinishHelper.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }

}
