package wu.mydemo.function.pushRefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.BaseActivity;
import wu.mydemo.R;
import wu.mydemo.function.pushRefresh.adapter.PushRefreshAdapter;
import wu.mydemo.function.pushRefresh.bgarefresh.BGANormalRefreshViewHolder;
import wu.mydemo.function.pushRefresh.bgarefresh.BGARefreshLayout;
import wu.mydemo.main.adapter.MainRecycleAdapter;
import wu.mydemo.utils.SystemTool;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/26 14:30
 * 邮箱： descriable
 */
public class PushRefreshActivity extends BaseActivity {


    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.rl_fresh_layout)
    BGARefreshLayout rlFreshLayout;


    private final int TYPE_REFRESH  = 0;//下拉刷新
    private final int TYPE_LOADMORE = 1;//上拉加载

    private PushRefreshAdapter mAdapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected int setContentViewId() {
        return R.layout.activity_push_refresh;
    }

    @Override
    protected void setUpView() {
        initData();
        initRecycleView();
        rlFreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(mContext, true));
        rlFreshLayout.setDelegate(mDelegate);
        rlFreshLayout.beginRefreshing();
    }

    @Override
    protected void init() {

    }

    /**
     * 监听 刷新或者上拉
     */
    private BGARefreshLayout.BGARefreshLayoutDelegate mDelegate = new BGARefreshLayout.BGARefreshLayoutDelegate() {
        @Override
        public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
            initData();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mHandler.sendEmptyMessage(TYPE_REFRESH);
                }
            },2000);
        }

        @Override
        public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
            if(stringList.size()>30){
                SystemTool.showSnackeBar(recycleView,"没有更多数据");
                return false;//不显示更多加载
            }else{
                stringList.add("" + (stringList.size()+1));
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mHandler.sendEmptyMessage(TYPE_LOADMORE);
                    }
                },2000);
            }
            return true;
        }
    };

    private void initData(){
        stringList.clear();
        for (int i=0;i<5;i++){
            stringList.add(""+i);
        }
    }

    private void initRecycleView(){
        mAdapter = new PushRefreshAdapter(this,stringList);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(mAdapter);
    }




    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case TYPE_REFRESH:
                    mAdapter.notifyDataSetChanged();
                    rlFreshLayout.endRefreshing();
                    break;
                case TYPE_LOADMORE:
                    mAdapter.notifyDataSetChanged();
                    rlFreshLayout.endLoadingMore();
                    break;
            }
        }
    };


    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, PushRefreshActivity.class);
        context.startActivity(intent);
    }
}
