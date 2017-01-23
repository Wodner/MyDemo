package wu.mydemo.function.photoview.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import wu.mydemo.R;
import wu.mydemo.utils.SystemTool;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2017/1/22 18:51
 * 邮箱： descriable
 */
public class PhotoViewAdapter extends RecyclerView.Adapter<PhotoViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<String> list;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private OnLongRecyclerViewItemClickListener onLongRecyclerViewItemClickListener;

    public PhotoViewAdapter(Context context,List<String> list){
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
        View v = LayoutInflater.from(parent.getContext()) .inflate(R.layout.item_list_push_refresh, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
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

        holder.imageView.setTag(list.get(position));

        if(holder.imageView.getTag()!=null && holder.imageView.getTag().equals(list.get(position))){
            Picasso.with(mContext)
                    .load(list.get(position))
//                    .resize(100, 100)
//                    .centerCrop()
                    .into(holder.imageView);
        }



    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.iv_image);
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
