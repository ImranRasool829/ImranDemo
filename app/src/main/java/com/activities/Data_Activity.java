package com.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adapter.Data_Result_Adapter;
import com.bean.DataModel;
import com.example.myapp.R;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.networks.AppController;
import com.networks.WebInterface;
import com.utils.DialogUtil;
import com.utils.UtilFunctions;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;

public class Data_Activity extends AppCompatActivity {

    private RecyclerView rv_data;
    private Context context;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        context = this;

        rv_data = findViewById(R.id.rv_data);

        API_DATA_GET_RESULT();
    }

    private void API_DATA_GET_RESULT() {

        if (DialogUtil.checkInternetConnection(context)) {
            final ProgressDialog progress = new ProgressDialog(context, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT);
            progress.setMessage("Loading !! Please wait...");
            progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();
            WebInterface Service = AppController.getRetrofitInstance(WebInterface.class);
            Call<JsonObject> response = Service.API_DATA_GET_RESULT();
            response.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(@NotNull Call<JsonObject> call, @NotNull retrofit2.Response<JsonObject> response) {
                    progress.dismiss();
                    if (response.body() != null) {
                        try {

                            String jsonObject = response.body().toString();
                            JSONObject json_res = new JSONObject(jsonObject);

                            DataModel dataModel = new Gson().fromJson(json_res.toString(), DataModel.class);
                            if (dataModel != null && dataModel.getResults().size() > 0) {

                                rv_data.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                                Data_Result_Adapter adapter = new Data_Result_Adapter(context, dataModel.getResults());
                                rv_data.setAdapter(adapter);
                                adapter.notifyDataSetChanged();

                            } else {
                                UtilFunctions.showToast("Data Not Found !!", context);
                            }

                        } catch (Exception e) {
                            UtilFunctions.showToast(e.getMessage(), context);
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                    progress.dismiss();
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            DialogUtil.showNoConnectionDialog(context);
        }
    }


}