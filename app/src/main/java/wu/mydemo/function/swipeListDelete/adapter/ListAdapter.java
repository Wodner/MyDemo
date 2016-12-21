package wu.mydemo.function.swipeListDelete.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wu.mydemo.R;


/**
 * Created by Administrator on 2016/12/7.
 */
public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> entityList;
    private OnItemDeleteClickListener onItemDeleteClickListener;

    public ListAdapter(Context context,List<String> list){
        this.mContext = context;
        this. entityList = list;
    }

    @Override
    public int getCount() {
        return entityList.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public Object getItem(int i) {
        return entityList.get(i);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;
        ViewHolder viewHolder;
        if (convertView==null){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_swipe_layout_list,null);
            viewHolder = new ViewHolder();
            viewHolder.tvMsg = (TextView)view.findViewById(R.id.tvMsg);
            viewHolder.tvDelete = (TextView)view.findViewById(R.id.tv_delete);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag(); // 重新获取ViewHolder
        }
        viewHolder.tvMsg.setText(entityList.get(position));
        if(onItemDeleteClickListener!=null){
            viewHolder.tvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemDeleteClickListener.onItemDelete(view,position);
                }
            });
        }
        return view;
    }


    class ViewHolder {
        TextView tvMsg;
        ImageView ivPhoto;
        TextView tvDelete;
    }


    public void setData(List<String> list){
        this.entityList = list;
        notifyDataSetChanged();
    }

    public void setOnItemDeleteClickListener(OnItemDeleteClickListener listener){
        this.onItemDeleteClickListener = listener;
    }

    public interface OnItemDeleteClickListener{
        void onItemDelete(View v, int postion);
    }



}
