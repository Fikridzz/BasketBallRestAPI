package com.example.fikridzakwan.basketballrestapi;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fikridzakwan.basketballrestapi.API.ApiClient;
import com.example.fikridzakwan.basketballrestapi.API.ApiInterface;
import com.example.fikridzakwan.basketballrestapi.Model.ClubData;
import com.example.fikridzakwan.basketballrestapi.Model.ClubResponse;
import com.example.fikridzakwan.basketballrestapi.Model.Constans;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.imgStadium)
    ImageView imgStadium;
    @BindView(R.id.myToolbar)
    Toolbar myToolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.txtNamaStadium)
    TextView txtNamaStadium;
    @BindView(R.id.imgLogoClub)
    ImageView imgLogoClub;
    @BindView(R.id.txtDetailClub)
    TextView txtDetailClub;
    @BindView(R.id.txtTempatStadium)
    TextView txtTempatStadium;

    private Bundle bundle;
    private List<ClubData> clubDataList;
    private ApiInterface apiInterface;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();

        if (bundle != null) {
            id = Integer.valueOf(bundle.getString(Constans.id));
        }

        clubDataList = new ArrayList<>();
        getData();
    }

    private void getData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ClubResponse> call = apiInterface.getDetail(id);
        call.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                ClubResponse clubResponse = response.body();
                clubDataList = clubResponse.getTeams();
                txtNamaStadium.setText(clubDataList.get(0).getStrStadiumThumb());
                txtDetailClub.setText(clubDataList.get(0).getStrDescriptionEN());
                txtTempatStadium.setText(clubDataList.get(0).getStrStadiumLocation());
                Glide.with(DetailActivity.this).load(clubDataList.get(0).getStrStadiumThumb()).into(imgStadium);
                Glide.with(DetailActivity.this).load(clubDataList.get(0).getStrTeamBadge()).into(imgLogoClub);
            }

            @Override
            public void onFailure(Call<ClubResponse> call, Throwable t) {
                Toast.makeText(DetailActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
