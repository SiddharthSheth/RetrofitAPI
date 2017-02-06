package ln.retrofitapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import ln.retrofitapi.R;
import ln.retrofitapi.response.DataResponse;

public class DataActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddress, btnFormatedAdd, btnGeomatry, btnPlaceid, btnTypes;

    protected String placeid, jsonString;
    DataResponse dataResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initView();

        dataResponse = new DataResponse();

        Bundle extras = getIntent().getExtras();

        placeid = extras.getString("placeid");

        jsonString = extras.getString("JSONString");

    }

    public void initView()
    {
        btnAddress = (Button) findViewById(R.id.btn_addresscomponent);
        btnFormatedAdd = (Button) findViewById(R.id.btn_formatted_address);
        btnGeomatry = (Button) findViewById(R.id.btn_geometry);
        btnPlaceid = (Button) findViewById(R.id.btn_place_id);
        btnTypes = (Button) findViewById(R.id.btn_types);
        btnAddress.setOnClickListener(this);
        btnFormatedAdd.setOnClickListener(this);
        btnGeomatry.setOnClickListener(this);
        btnPlaceid.setOnClickListener(this);
        btnTypes.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        dataResponse = new Gson().fromJson(jsonString,DataResponse.class);

        switch (view.getId()) {

            case R.id.btn_addresscomponent:

                Intent intentObject1 = new Intent(DataActivity.this,ListActivity.class);
                intentObject1.putExtra("placeid",placeid);
                intentObject1.putExtra("JSONString",jsonString);
                startActivity(intentObject1);

//                showToast(dataResponse.getResults().get(0).getPlaceId());
                break;

            case R.id.btn_formatted_address:

                if (placeid.equals("ChIJ9yHfgj0DzYcRwkZr0WiJZKw"))
                {
                    showToast(dataResponse.getResults().get(0).getFormattedAddress());
                }
                if (placeid.equals("ChIJL273R381MYgRyg23f_bs1Nc"))
                {
                    showToast(dataResponse.getResults().get(1).getFormattedAddress());
                }
                break;

            case R.id.btn_geometry:

                Intent intentObject3 = new Intent(DataActivity.this,ListActivity.class);
                intentObject3.putExtra("placeid",placeid);
                intentObject3.putExtra("JSONString",jsonString);
                startActivity(intentObject3);
                break;

            case R.id.btn_place_id:

                if (placeid.equals("ChIJ9yHfgj0DzYcRwkZr0WiJZKw"))
                {
                    showToast(dataResponse.getResults().get(0).getPlaceId());
                }
                if (placeid.equals("ChIJL273R381MYgRyg23f_bs1Nc"))
                {
                    showToast(dataResponse.getResults().get(1).getPlaceId());
                }
                break;

            case R.id.btn_types:

                if (placeid.equals("ChIJ9yHfgj0DzYcRwkZr0WiJZKw"))
                {
                    showToast(dataResponse.getResults().get(0).getTypes().get(0));
                }
                if (placeid.equals("ChIJL273R381MYgRyg23f_bs1Nc"))
                {
                    showToast(dataResponse.getResults().get(1).getTypes().get(1));
                }

                break;

        }

    }

    private void showToast(String message) {
        Toast.makeText(DataActivity.this, message, Toast.LENGTH_LONG).show();
    }
}
