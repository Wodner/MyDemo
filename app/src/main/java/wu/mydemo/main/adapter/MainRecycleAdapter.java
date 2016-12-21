package wu.mydemo.main.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import wu.mydemo.R;
import wu.mydemo.utils.SystemTool;

/**
 * 主页面适配器
 * Created by Administrator on 2016/12/20.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

    public MainRecycleAdapter(Context context,List<String> list){
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
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_main, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));
        holder.tvName.setBackgroundColor(SystemTool.getRandColorCode());
        //判断是否设置了监听器
        if(onRecyclerViewItemClickListener != null){
            //为ItemView设置监听器
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition(); // 1
                    onRecyclerViewItemClickListener.onItemClick(v ,list.get(position),holder.getLayoutPosition());
                }
            });
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        CardView cardView;
        public MyViewHolder(View view){
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            cardView = (CardView)view.findViewById(R.id.cardview);
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion);
    }
    public void setOnItemClickListener(OnLongRecyclerViewItemClickListener listener) {
        this.onLongRecyclerViewItemClickListener = listener;
    }
    public  interface OnLongRecyclerViewItemClickListener {
        void onItemClick(View v, String msg,int postion);
    }

}
