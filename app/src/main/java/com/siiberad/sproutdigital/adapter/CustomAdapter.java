package com.siiberad.sproutdigital.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.siiberad.sproutdigital.R;
import com.siiberad.sproutdigital.model.DataModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.KontakViewHolder> {
    private List<DataModel> dataModelList = new ArrayList<>();
    private Context context;
    private View.OnClickListener onClickListener;

    public CustomAdapter(Context context, List<DataModel> dataModelList, View.OnClickListener onClickListener) {
        this.context = context;
        this.dataModelList.addAll(dataModelList);
        this.onClickListener = onClickListener;
    }

    @Override
    public KontakViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(context).inflate(R.layout.custom_row, parent, false);
        return new KontakViewHolder(theView);
    }

    @Override
    public void onBindViewHolder(final KontakViewHolder holder, final int position) {
        DataModel dataModel = dataModelList.get(position);

        holder.itemView.setTag(dataModel);
        holder.itemView.setOnClickListener(onClickListener);

        Picasso.get().load(dataModel.getAvatar()).into(holder.img_avatar);
        holder.img_avatar.setColorFilter(Color.parseColor("#9d47ff"), PorterDuff.Mode.LIGHTEN);
        holder.txt_firstname.setText(dataModel.getFirst_name());
        holder.txt_email.setText(dataModel.getEmail());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }


    public class KontakViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_firstname, txt_email;
        private ImageView img_avatar;

        public KontakViewHolder(View itemView) {
            super(itemView);
            txt_firstname = itemView.findViewById(R.id.txt_firstname);
            txt_email = itemView.findViewById(R.id.txt_email);
            img_avatar = itemView.findViewById(R.id.img_avatar);
        }
    }
}

