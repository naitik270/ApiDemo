package com.jakb.apidemo.Categories;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakb.apidemo.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategories extends RecyclerView.Adapter<AdapterCategories.ViewHolder> {

    View itemView;
    List<ClsCategoryList> lst = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public AdapterCategories(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }
    private OnClickListenerCall mOnClickListenerCall;

    public interface OnClickListenerCall {
        void OnItemClick(ClsCategoryList obj, int position);
    }

    public void setOnThumbImgClick(OnClickListenerCall mOnClickListenerCall) {
        this.mOnClickListenerCall = mOnClickListenerCall;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.row_category, parent, false);
        return new ViewHolder(itemView);
    }

    public void updateList(ClsCategoryList obj) {
        lst.set(lst.indexOf(obj), obj);
        notifyDataSetChanged();
    }

    public void addList(List<ClsCategoryList> list) {
        this.lst = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClsCategoryList currentObj = lst.get(position);

        Log.e("--params--", "size: " + currentObj.getTitle());

        holder.txt_name.setText(currentObj.getTitle().toUpperCase());
        holder.BindThumbImgDownload(currentObj, position, mOnClickListenerCall);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout ll_header;
        TextView txt_name;

        public ViewHolder(View itemView) {
            super(itemView);
            ll_header = itemView.findViewById(R.id.ll_header);
            txt_name = itemView.findViewById(R.id.txt_name);
        }

        void BindThumbImgDownload(final ClsCategoryList imageList, int position,
                                  OnClickListenerCall onMainImageClick) {
            txt_name.setOnClickListener(view -> {
                onMainImageClick.OnItemClick(imageList, position);
            });
        }
    }
}