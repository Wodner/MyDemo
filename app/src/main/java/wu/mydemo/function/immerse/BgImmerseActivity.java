package wu.mydemo.function.immerse;

import android.app.Activity;
import android.content.Intent;

import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * 背景沉浸
 * Created by Administrator on 2016/12/22.
 */
public class BgImmerseActivity extends BaseActivity {

    @Override
    protected int setContentViewId() {
        return 0;
    }

    @Override
    protected void setUpView() {
        setRootLayoutBackgroundRes(R.mipmap.ic_immerse_bg);
        setToolbarBackgroundColor(0x00000000);
        MyStatusBarUtil.setStatusTransparent(mContext,true);
    }

    @Override
    protected void init() {

    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, BgImmerseActivity.class);
        context.startActivity(intent);
    }
}
