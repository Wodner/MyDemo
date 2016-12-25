package wu.mydemo.function.coordinatorLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/23 14:35
 * 邮箱： descriable
 */
public class CoordinatorLayoutActivity extends BaseActivity {

    @Bind(R.id.btn_appbar)
    Button btnAppbar;
    @Bind(R.id.btn_collapsing)
    Button btnCollapsing;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_coordinator_layout;
    }

    @Override
    protected void setUpView() {

    }


    @Override
    protected void init() {

    }


    @OnClick({ R.id.btn_appbar, R.id.btn_collapsing})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_appbar:
                WithAppbarActivity.startAction(mContext);
                break;
            case R.id.btn_collapsing:
                WithCollapsingActivity.startAction(mContext);
                break;
        }
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, CoordinatorLayoutActivity.class);
        context.startActivity(intent);
    }
}
