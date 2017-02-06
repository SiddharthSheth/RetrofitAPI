package ln.retrofitapi.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import ln.retrofitapi.R;
import ln.retrofitapi.adapter.DemoAdapter;

public class ListActivity extends AppCompatActivity {

    RecyclerView rvList;
    DemoAdapter demoAdapter;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        rvList = (RecyclerView)findViewById(R.id.rv_listitems);

        extras = getIntent().getExtras();

        demoAdapter = new DemoAdapter(extras.getString("JSONString"),extras.getString("placeid"));

        rvList.setAdapter(demoAdapter);

    }
}
