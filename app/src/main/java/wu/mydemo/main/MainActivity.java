package wu.mydemo.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.immerse.ImmerseActivity;
import wu.mydemo.function.readme.ReadMeActivity;
import wu.mydemo.function.swipeBack.MySwipeBackActivity;
import wu.mydemo.function.swipeListDelete.SwipeListDeleteActivity;
import wu.mydemo.function.toggleButton.ToggleButtonActivity;
import wu.mydemo.main.adapter.MainRecycleAdapter;
import wu.mydemo.utils.MyStatusBarUtil;
import wu.mydemo.utils.SystemTool;

/**
 * 主界面
 */
public class MainActivity extends BaseActivity {

    @Bind(R.id.main_layout)
    RelativeLayout mainLayout;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    private long exitTime = 0;
    private MainRecycleAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();


    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void setUpView() {
        setTitleText("功能页面");
        initRecycleView();
    }

    @Override
    protected void init() {

    }



    private void initRecycleView(){
        stringList.clear();
        Collections.addAll(stringList, getResources().getStringArray(R.array.itme_function));
//        stringList = Arrays.asList(getResources().getStringArray(R.array.itme_function));
        recycleAdapter = new MainRecycleAdapter(this,stringList);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        recycleView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(onRecyclerViewItemClickListener);
    }


    /**
     * RecycleView 点击事件
     */
    private MainRecycleAdapter.OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = new MainRecycleAdapter.OnRecyclerViewItemClickListener() {
        @Override
        public void onItemClick(View v, String msg,int position) {
            switch (position){
                case 0:
                    ReadMeActivity.startAction(mContext);
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    ImmerseActivity.startAction(mContext);
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    MySwipeBackActivity.startAction(mContext);
                    break;
                case 17:
                    ToggleButtonActivity.startAction(mContext);
                    break;
                case 18:
                    SwipeListDeleteActivity.startAction(mContext);
                    break;
                default:
                    SystemTool.showSnackeBar(mainLayout,position + "");
                    break;

            }

        }
    };




    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    /**
     * 按两次退出
     *
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                SystemTool.showSnackeBar(mainLayout, "再按一次退出应用");
                exitTime = System.currentTimeMillis();
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
