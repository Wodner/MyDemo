package wu.mydemo.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * 系统工具类
 * Created by Administrator on 2016/12/20.
 */
public class SystemTool {

    public static void showSnackeBar(View view,String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

}
