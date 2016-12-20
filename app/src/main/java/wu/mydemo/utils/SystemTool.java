package wu.mydemo.utils;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.Random;

/**
 * 系统工具类
 * Created by Administrator on 2016/12/20.
 */
public class SystemTool {

    /**
     * Snackbar
     * @param view
     * @param msg
     */
    public static void showSnackeBar(View view,String msg){
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * 获取十六进制的颜色代码.例如  "#6E36B4" , For HTML ,
     * @return String
     */
    public static int getRandColorCode(){
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        return ranColor;
    }
}
