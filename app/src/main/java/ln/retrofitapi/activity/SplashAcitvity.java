package ln.retrofitapi.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import ln.retrofitapi.R;
import ln.retrofitapi.api.ApiFactory;
import ln.retrofitapi.request.DataRequest;
import ln.retrofitapi.response.DataResponse;
import ln.retrofitapi.service.DataService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashAcitvity extends AppCompatActivity {

    DataRequest dataRequest;
    DataResponse dataResponse;
    ProgressDialog loading;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {

                dataRequest = new DataRequest();
                dataResponse = new DataResponse();
                // This method will be executed once the timer is over
                // Start your app main activity

                //While the app fetched data we are displaying a progress dialog
                loading = ProgressDialog.show(SplashAcitvity.this,"Fetching Data","Please wait...",false,false);

                DataService dataService = ApiFactory.createService(DataService.class);

                Call<JsonObject> dataRequestCall = dataService.getResult();

                dataRequestCall.enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                        loading.dismiss();
                        if (response.isSuccessful()) {
                            Log.e("Response",response.body().toString());

                            dataResponse = new Gson().fromJson(response.body(),DataResponse.class);

                            if (dataResponse.getStatus().equals("OK")) {

                                Intent i = new Intent(SplashAcitvity.this, DashboardActivity.class);
                                i.putExtra("JSONString",response.body().toString());
                                startActivity(i);

                            } else {
                                showToast(dataResponse.getStatus());
                            }
                        } else {
                            showToast(getString(R.string.str_please_try_again));
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        loading.dismiss();

                    }
                });



//                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);

    }

    private void showToast(String message) {
        Toast.makeText(SplashAcitvity.this, message, Toast.LENGTH_SHORT).show();
    }
}
