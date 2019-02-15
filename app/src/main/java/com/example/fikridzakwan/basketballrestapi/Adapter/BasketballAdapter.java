package com.example.fikridzakwan.basketballrestapi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.fikridzakwan.basketballrestapi.DetailActivity;
import com.example.fikridzakwan.basketballrestapi.MainActivity;
import com.example.fikridzakwan.basketballrestapi.Model.ClubData;
import com.example.fikridzakwan.basketballrestapi.Model.Constans;
import com.example.fikridzakwan.basketballrestapi.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BasketballAdapter extends RecyclerView.Adapter<BasketballAdapter.ViewHolder> {

    private Context context;
    private List<ClubData> clubDataList;
    private Bundle bundle;

    public BasketballAdapter(Context context, List<ClubData> clubDataList) {
        this.context = context;
        this.clubDataList = clubDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_club, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final ClubData clubData = clubDataList.get(i);

        viewHolder.txtNamaClub.setText(clubData.getStrTeam());
        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);
        Glide.with(context).load(clubData.getStrTeamBadge()).apply(options).into(viewHolder.imgClub);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bundle = new Bundle();
                bundle.putString(Constans.id,clubData.getIdTeam());
                context.startActivity(new Intent(context,DetailActivity.class).putExtras(bundle));
            }
        });

    }

    @Override
    public int getItemCount() {
        return clubDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_club)
        ImageView imgClub;
        @BindView(R.id.txtNamaClub)
        TextView txtNamaClub;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
