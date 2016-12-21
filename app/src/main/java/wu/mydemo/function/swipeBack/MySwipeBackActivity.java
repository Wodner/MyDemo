package wu.mydemo.function.swipeBack;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;

import wu.mydemo.BaseActivity;
import wu.mydemo.R;

/**
 * Created by Administrator on 2016/12/21.
 */
public class MySwipeBackActivity extends BaseActivity{


    @Override
    protected int setContentViewId() {
        return R.layout.activity_swipe;
    }

    @Override
    protected void setUpView() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void init() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.finish();
        return super.onSupportNavigateUp();
    }
    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, MySwipeBackActivity.class);
        context.startActivity(intent);
    }



}
