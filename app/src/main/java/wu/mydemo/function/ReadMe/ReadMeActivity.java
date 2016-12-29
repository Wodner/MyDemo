package wu.mydemo.function.ReadMe;


import android.app.Activity;
import android.content.Intent;
import android.widget.TextView;

import butterknife.Bind;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
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
    }

    @Override
    protected void init() {
        tvMsg.setText(SystemTool.getAssetsTxt(this,"readme.txt"));
    }



    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ReadMeActivity.class);
        context.startActivity(intent);
    }
}
