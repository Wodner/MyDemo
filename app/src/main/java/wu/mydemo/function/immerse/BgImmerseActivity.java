package wu.mydemo.function.immerse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.R;
import wu.mydemo.utils.MyStatusBarUtil;

/**
 * 背景沉浸
 * Created by Administrator on 2016/12/22.
 */
public class BgImmerseActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bg_immerse);
        ButterKnife.bind(this);
        MyStatusBarUtil.setStatusTransparent(this, false);

        toolbar.setTitle("背景沉浸");
        toolbar.setBackgroundColor(0x00000000);
        if(toolbar!=null){
            setSupportActionBar(toolbar);
            //设置toolbar后调用setDisplayHomeAsUpEnabled
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    /**
     * @param context
     */
    public static void startAction(Activity context) {
        Intent intent = new Intent(context, BgImmerseActivity.class);
        context.startActivity(intent);
    }
}
