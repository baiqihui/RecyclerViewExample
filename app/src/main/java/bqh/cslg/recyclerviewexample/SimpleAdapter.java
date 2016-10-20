package bqh.cslg.recyclerviewexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by baiqihui on 2016/10/19.
 */

public class SimpleAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    List<String> mDatas;

    OnItemClickListener onItemClickListener = null;

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public SimpleAdapter(Context mContext,List<String> mDatas){
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
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


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_simple_textview,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null) {
                    onItemClickListener.OnItemClick(v, holder.getLayoutPosition());
                }
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener!=null) {
                    onItemClickListener.OnItemLongClick(v, position);
                }
                return true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
class MyViewHolder extends RecyclerView.ViewHolder {
    TextView tv;
    FrameLayout frameLayout;

    public MyViewHolder(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.id_tv);
        frameLayout = (FrameLayout) itemView.findViewById(R.id.frame);
    }
}
