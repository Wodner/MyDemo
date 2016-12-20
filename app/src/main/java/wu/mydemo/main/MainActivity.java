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
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.R;
import wu.mydemo.main.adapter.MainRecycleAdapter;
import wu.mydemo.utils.SystemTool;

/**
 * 主界面
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_layout)
    RelativeLayout mainLayout;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    private long exitTime = 0;
    private MainRecycleAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        SystemTool.showSnackeBar(mainLayout, "Welcome！");
        initRecycleView();
    }

    private void initRecycleView(){
        stringList.clear();
        for(int i=0;i<50;i++){
            stringList.add("第" + i+ "个");
        }
        recycleAdapter = new MainRecycleAdapter(this,stringList);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL));
        recycleView.setAdapter(recycleAdapter);
    }


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
