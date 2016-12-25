package wu.mydemo.function.immerse.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wu.mydemo.R;
import wu.mydemo.utils.SystemTool;

/**
 * 颜色选择适配
 * Created by Administrator on 2016/12/22.
 */
public class ColorRecycleAdapter extends RecyclerView.Adapter<ColorRecycleAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

    public ColorRecycleAdapter(Context context,List<String> list){
        this.mContext = context;
        this.list = list;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.itme_list_color_immerse, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.tvMsg.setText(list.get(position));
        final int color = SystemTool.getRandColorCode();
        holder.tvMsg.setBackgroundColor(color);
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onRecyclerViewItemClickListener.onItemClick(v ,list.get(position),position,color);
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvMsg;
        public MyViewHolder(View view){
            super(view);
            tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion,int color);
    }
    public void setOnItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }
    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion);
    }
}
