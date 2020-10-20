package com.jakb.apidemo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakb.apidemo.Classes.ClsCategory;
import com.jakb.apidemo.Classes.ClsList;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterThumbList extends RecyclerView.Adapter<AdapterThumbList.ViewHolder> {

    View itemView;
    List<ClsList> lst = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    int position = 0;

    public AdapterThumbList(Context mContext) {
        this.mContext = mContext;

        mInflater = LayoutInflater.from(mContext);
    }


    private OnClickListenerCall mOnClickListenerCall;

    public interface OnClickListenerCall {
        void OnItemClick(ClsList obj, int position);
    }

    public void setOnThumbImgClick(OnClickListenerCall mOnClickListenerCall) {
        this.mOnClickListenerCall = mOnClickListenerCall;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.row_thumb_list, parent, false);
        return new ViewHolder(itemView);
    }

    public void updateList(ClsList clsMerchantResponseList) {
        lst.set(lst.indexOf(clsMerchantResponseList), clsMerchantResponseList);
        notifyDataSetChanged();
    }

    public void addList(List<ClsList> list) {
        this.lst = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClsList currentObj = lst.get(position);

        if (currentObj.isDownloaded()){
            holder.iv_download.setVisibility(View.GONE);
        }else {
            holder.iv_download.setVisibility(View.VISIBLE);
        }

        Picasso.get().load(currentObj.getThumbUrl()).into(holder.iv_thumb);

//        Picasso.get().load(currentObj.getThumbUrl()).memoryPolicy(MemoryPolicy.NO_CACHE).into(holder.iv_thumb);

        List<ClsCategory> lst = new ArrayList<>();
        lst = currentObj.getCategories();

        for (ClsCategory obj : lst) {
            Log.e("--val--", "getId: " + obj.getId());
            Log.e("--val--", "getTitle: " + obj.getTitle());
        }


        holder.BindThumbImgDownload(currentObj, position, mOnClickListenerCall);
    }


    @Override
    public int getItemCount() {
        return lst.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_thumb;
        ImageView iv_download;
        LinearLayout ll_header, ll_category, cast_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_thumb = itemView.findViewById(R.id.iv_thumb);
            iv_download = itemView.findViewById(R.id.iv_download);
            ll_header = itemView.findViewById(R.id.ll_header);
            ll_category = itemView.findViewById(R.id.ll_category);
            cast_layout = itemView.findViewById(R.id.cast_layout);
        }

        void BindThumbImgDownload(final ClsList imageList, int position,
                                  OnClickListenerCall onMainImageClick) {
            ll_header.setOnClickListener(view -> {
                onMainImageClick.OnItemClick(imageList, position);
            });
        }
    }
}