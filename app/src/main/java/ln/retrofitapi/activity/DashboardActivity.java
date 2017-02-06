package ln.retrofitapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import ln.retrofitapi.R;
import ln.retrofitapi.request.DataRequest;
import ln.retrofitapi.response.DataResponse;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener{

    private AppCompatButton btnObj1, btnObj2;

    DataRequest dataRequest;
    DataResponse dataResponse;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataRequest = new DataRequest();
        dataResponse = new DataResponse();

        initView();

        extras = getIntent().getExtras();

    }

    public void initView()
    {
        btnObj1 = (AppCompatButton) findViewById(R.id.btn_object1);
        btnObj2 = (AppCompatButton) findViewById(R.id.btn_object2);
        btnObj1.setOnClickListener(this);
        btnObj2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        dataResponse = new Gson().fromJson(extras.getString("JSONString"),DataResponse.class);

        switch (view.getId()) {
            case R.id.btn_object1:
                Intent intentObject1 = new Intent(DashboardActivity.this,DataActivity.class);
                intentObject1.putExtra("placeid",dataResponse.getResults().get(0).getPlaceId());
                intentObject1.putExtra("JSONString",extras.getString("JSONString"));
                startActivity(intentObject1);
//                showToast(dataResponse.getResults().get(0).getPlaceId());
                break;

            case R.id.btn_object2:
                Intent intentObject2 = new Intent(DashboardActivity.this,DataActivity.class);
                intentObject2.putExtra("placeid",dataResponse.getResults().get(1).getPlaceId());
                intentObject2.putExtra("JSONString",extras.getString("JSONString"));
                startActivity(intentObject2);
                break;
        }

    }

    private void showToast(String message) {
        Toast.makeText(DashboardActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}
