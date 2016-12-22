package wu.mydemo.function.swipeBack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;

import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.swipeBack.app.SwipeBackActivity;
import wu.mydemo.function.swipeBack.helper.SwipeBackLayout;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MySwipeBackActivity extends SwipeBackActivity{


    @Override
    protected int setContentViewId() {
        return R.layout.activity_swipe;
    }

    @Override
    protected void setUpView() {
        setTitleText("滑动返回");
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, MySwipeBackActivity.class);
        context.startActivity(intent);
    }



}
