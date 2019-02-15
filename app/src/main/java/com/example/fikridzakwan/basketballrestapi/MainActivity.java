package com.example.fikridzakwan.basketballrestapi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.fikridzakwan.basketballrestapi.API.ApiClient;
import com.example.fikridzakwan.basketballrestapi.API.ApiInterface;
import com.example.fikridzakwan.basketballrestapi.Adapter.BasketballAdapter;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvClub)
    RecyclerView rvClub;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private List<ClubData> clubDataList;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        clubDataList = new ArrayList<>();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });

        showProgress();

        getData();
    }

    private void getData() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ClubResponse> call = apiInterface.getClub(Constans.idBasket);
        call.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse> call, Response<ClubResponse> response) {
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);
                ClubResponse clubResponse = response.body();
                clubDataList = clubResponse.getTeams();
                rvClub.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
                rvClub.setAdapter(new BasketballAdapter(MainActivity.this, clubDataList));
            }

            @Override
            public void onFailure(Call<ClubResponse> call, Throwable t) {
                progressDialog.dismiss();
                swipeRefresh.setRefreshing(false);
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgress() {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading •••");
        progressDialog.show();
    }
}
