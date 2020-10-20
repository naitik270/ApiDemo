package com.jakb.apidemo.MyClass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jakb.apidemo.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterThumbListNew extends RecyclerView.Adapter<AdapterThumbListNew.ViewHolder> {

    View itemView;
    List<ImageList> lst = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;

    public AdapterThumbListNew(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }


    public void AddItem(List<ImageList> lists) {
        this.lst = lists;
        notifyDataSetChanged();
        Log.e("--val--", "AddItem: ");
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        itemView = mInflater.inflate(R.layout.row_thumb_list, parent, false);
        return new ViewHolder(itemView);
    }

    private OnClickListenerCall mOnClickListenerCall;

    public interface OnClickListenerCall {
        void OnItemClick(ImageList obj, int position);
    }

    public void setOnThumbImgClick(OnClickListenerCall mOnClickListenerCall) {
        this.mOnClickListenerCall = mOnClickListenerCall;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageList current = lst.get(position);


//        Picasso.get().load(current.getThumb_url()).fit().into(holder.iv_thumb);


//        Uri uri = Uri.parse("android.resource://" + mContext.getPackageName() + "/drawable/ic_no_image.xml");
//
//        if (current.getImage_url().contains("")) {
//            Picasso.get().load(uri)
//                    .placeholder(R.drawable.ic_no_image)
//                    .resize(120, 60)
//                    .into(holder.iv_thumb);
//        } else {
//        }

        Log.e("--val--", "onBindViewHolder: " + current.getThumb_url());
        Log.e("--val--", "onBindViewHolder: " + current.getTitle());

        List<ImageList.Tags> lst = new ArrayList<>();
        lst = current.getTags();


        for (ImageList.Tags obj : lst) {
            Log.e("--val--", "obj: " + obj.getTitle());
        }

////
////            List<ClsTag> list = new ArrayList<>();
////
////            list = StreamSupport.stream(current.ge())
////                    .filter(s -> s.getWeekDay().equalsIgnoreCase(ClsGlobal.getCurrentDay()))
////                    .collect(Collectors.toList());
//            for (ClsTag obj : lst) {
//                Log.e("--val--", "obj: " + obj.getTitle());
//            }
//


        Picasso.get().load(current.getThumb_url()).into(holder.iv_thumb);

        holder.BindThumbImgDownload(current, position, mOnClickListenerCall);
    }

    @Override
    public int getItemCount() {
        return lst.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        ImageView iv_thumb;
        LinearLayout ll_header;

        public ViewHolder(View itemView) {
            super(itemView);

            iv_thumb = itemView.findViewById(R.id.iv_thumb);
            ll_header = itemView.findViewById(R.id.ll_header);

        }

        void BindThumbImgDownload(final ImageList imageList, int position,
                                  OnClickListenerCall onMainImageClick) {
            ll_header.setOnClickListener(view -> {
                onMainImageClick.OnItemClick(imageList, position);
            });
        }

    }
}