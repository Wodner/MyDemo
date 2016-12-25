package wu.mydemo.function.immerse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.immerse.adapter.ColorRecycleAdapter;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * 纯色沉浸式
 * Created by Administrator on 2016/12/22.
 */
public class ColorImmerseActivity extends BaseActivity {


    @Bind(R.id.recycler)
    RecyclerView recycleView;
    @Bind(R.id.btn_selet_color)
    TextView btnSeletColor;

    private ColorRecycleAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();


    @Override
    protected int setContentViewId() {
        return R.layout.activity_color_immerse;
    }

    @Override
    protected void setUpView() {
        setTitleText("沉浸式着色");
    }

    @Override
    protected void init() {
        initRecyc();
    }


    private void initRecyc() {
        stringList.clear();
        Collections.addAll(stringList, getResources().getStringArray(R.array.itme_function));
        recycleAdapter = new ColorRecycleAdapter(this, stringList);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL));
        recycleView.setAdapter(recycleAdapter);
        recycleAdapter.setOnItemClickListener(new ColorRecycleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, String msg, int postion, int color) {
                MyStatusBarUtil.setStatusColor(ColorImmerseActivity.this, color);
                setToolbarBackgroundColor(color);
                btnSeletColor.setTextColor(color);

            }
        });
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, ColorImmerseActivity.class);
        context.startActivity(intent);
    }
}
