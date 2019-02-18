package com.bw.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.bean.Commodity;
import com.bw.wangqing0218.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by 1607c王晴
 * date 2019/2/18
 * Describe:
 */
public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.ViewHolder> {
    private Context context;
    private List<Commodity.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MlssAdapter(Context context, List<Commodity.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mlss_layout, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.pzsh_img.setImageURI(list.get(i).getMasterPic());
        viewHolder.pzsh_title.setText(list.get(i).getCommodityName());
        viewHolder.pzsh_price.setText("¥"+list.get(i).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView pzsh_img;
        private final TextView pzsh_title;
        private final TextView pzsh_price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pzsh_img = itemView.findViewById(R.id.mlss_img);
            pzsh_title = itemView.findViewById(R.id.mlss_title);
            pzsh_price = itemView.findViewById(R.id.mlss_price);
        }
    }

}
