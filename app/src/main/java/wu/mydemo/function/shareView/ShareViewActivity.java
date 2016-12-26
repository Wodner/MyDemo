package wu.mydemo.function.shareView;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.widget.ImageView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.coordinatorLayout.WithCollapsingActivity;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/26 10:55
 * 邮箱： descriable
 */
public class ShareViewActivity extends BaseActivity {


    @Bind(R.id.iv_share)
    ImageView ivShare;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_share_view;
    }

    @Override
    protected void setUpView() {

    }

    @Override
    protected void init() {

    }


    @OnClick(R.id.iv_share)
    public void onClick() {
        Intent intent = new Intent(mContext,WithCollapsingActivity.class);
        if(Build.VERSION.SDK_INT>=21){
            //创建单个共享元素
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext,ivShare,"share").toBundle());
//            //创建多个共享元素
//            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(mContext,
//            Pair.create(ivShare,"shareview_1"),
//            Pair.create(ivShare,"shareview_2").toBundle());
        }else{
            startActivity(intent);
        }
    }


    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ShareViewActivity.class);
        context.startActivity(intent);
    }
}
