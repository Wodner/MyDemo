package wu.mydemo.function.immerse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import wu.mydemo.BaseActivity;
import wu.mydemo.R;

/**
 * 沉浸式使用
 * Created by Administrator on 2016/12/21.
 */
public class ImmerseActivity extends BaseActivity{


    @Override
    protected int setContentViewId() {
        return R.layout.activity_immerse;
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
        Intent intent = new Intent(context, ImmerseActivity.class);
        context.startActivity(intent);
    }
}
