package bqh.cslg.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baiqihui on 2016/10/20.
 */

public class StaggeredAdapter extends RecyclerView.Adapter<StaggeredAdapter.MyViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    List<String> mDatas;

    private List<Integer> mHeight;

    OnItemClickListener onItemClickListener;

    public StaggeredAdapter(Context mContext,List<String> mDatas){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;

        mHeight = new ArrayList<>();
        for (int i=0;i<mDatas.size();i++){
            mHeight.add((int) (100+Math.random()*300));
        }
    }

    public void addData(int pos){
        mDatas.add(pos,"insert one");
        //notifyDataSetChanged();
        notifyItemInserted(pos);
    }

    public void deleteData(int pos){
        mDatas.remove(pos);
        //notifyDataSetChanged();
        notifyItemRemoved(pos);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    @Override
    public StaggeredAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_simple_textview,parent,false);
        StaggeredAdapter.MyViewHolder myViewHolder = new StaggeredAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final StaggeredAdapter.MyViewHolder holder, int position) {
        ViewGroup.LayoutParams pl = holder.itemView.getLayoutParams();
        pl.height = mHeight.get(position);
        holder.itemView.setLayoutParams(pl);
        holder.tv.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemClick(v,holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.OnItemLongClick(v,holder.getLayoutPosition());
                }
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_tv);
        }
}
}
