package wu.mydemo.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import wu.mydemo.R;

/**
 * 主页面适配器
 * Created by Administrator on 2016/12/20.
 */
public class MainRecycleAdapter extends RecyclerView.Adapter<MainRecycleAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> list;

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
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_main, parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvName;
        public MyViewHolder(View view){
            super(view);
            tvName = (TextView) view.findViewById(R.id.tv_name);
        }
    }


}
