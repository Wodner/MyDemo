package wu.mydemo.function.immerse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;

/**
 * 沉浸式使用
 * Created by Administrator on 2016/12/21.
 */
public class ImmerseActivity extends BaseActivity {


    @Bind(R.id.btn_color_immerse)
    Button btnColorImmerse;
    @Bind(R.id.btn_bg_immerse)
    Button btnBgImmerse;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_immerse;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void init() {

    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ImmerseActivity.class);
        context.startActivity(intent);
    }


    @OnClick({R.id.btn_color_immerse, R.id.btn_bg_immerse})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_color_immerse:
                ColorImmerseActivity.startAction(mContext);
                break;
            case R.id.btn_bg_immerse:
                BgImmerseActivity.startAction(mContext);
                break;
        }
    }
}
