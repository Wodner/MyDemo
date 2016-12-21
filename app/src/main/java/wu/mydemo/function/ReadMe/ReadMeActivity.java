package wu.mydemo.function.readme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.utils.MyStatusBarUtil;
import wu.mydemo.utils.SystemTool;

/**
 * 说明
 * Created by Administrator on 2016/12/21.
 */
public class ReadMeActivity extends BaseActivity {


    @Bind(R.id.tv_msg)
    TextView tvMsg;


    @Override
    protected int setContentViewId() {
        return R.layout.activity_readme;
    }

    @Override
    protected void setUpView() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void init() {
        tvMsg.setText(SystemTool.getAssetsTxt(this,"readme.txt"));
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
        Intent intent = new Intent(context, ReadMeActivity.class);
        context.startActivity(intent);
    }
}
