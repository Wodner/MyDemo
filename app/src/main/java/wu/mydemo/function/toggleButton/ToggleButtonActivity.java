package wu.mydemo.function.toggleButton;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.utils.SystemTool;

/**
 * 开关
 * Created by Administrator on 2016/12/21.
 */
public class ToggleButtonActivity extends BaseActivity {

    @Bind(R.id.btn_toggle)
    ToggleButton btnToggle;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_toggle_button;
    }

    @Override
    protected void setUpView() {
        ActionBar mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void init() {
        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    SystemTool.showSnackeBar(btnToggle,"TURN ON");
                }else{
                    SystemTool.showSnackeBar(btnToggle,"TURN OFF");
                }
            }
        });
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
        Intent intent = new Intent(context, ToggleButtonActivity.class);
        context.startActivity(intent);
    }
}
