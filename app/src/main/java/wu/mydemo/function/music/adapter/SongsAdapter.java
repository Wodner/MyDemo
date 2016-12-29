package wu.mydemo.function.music.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import wu.mydemo.R;
import wu.mydemo.function.music.model.Audio;

/**
 * 类描述：
 * 作  者：wuwen
 * 时  间：on 2016/11/19 at 17:29
 * 修改备注：
 */

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> implements View.OnClickListener{


    private List<Audio> audioList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener = null;
    public SongsAdapter(Context context){
        audioList = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        return audioList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_songs, parent, false);
        v.setOnClickListener(this);
        ViewHolder vh = new ViewHolder(v);
        return vh;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_song_name.setText(audioList.get(position).getTitle() );
        holder.tv_song_auther.setText(audioList.get(position).getArtist() + " - " + audioList.get(position).getAlbum());
        holder.itemView.setTag(audioList.get(position));
    }


    /**
     * ViewHolder
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_song_name;
        public TextView tv_song_auther;
        public ViewHolder(View v){
            super(v);
            tv_song_name = (TextView)v.findViewById(R.id.tv_song_name);
            tv_song_auther = (TextView)v.findViewById(R.id.tv_song_auther);
        }
    }

    /**
     * @param list
     */
    public void  setData(List<Audio> list){
        this.audioList = list;
        notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if(onRecyclerViewItemClickListener!=null){
            onRecyclerViewItemClickListener.onItemClick(view ,(Audio)view.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.onRecyclerViewItemClickListener = listener;
    }

    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View v, Audio audio);
    }
}
