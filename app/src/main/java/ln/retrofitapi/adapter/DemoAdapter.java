package ln.retrofitapi.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import ln.retrofitapi.R;
import ln.retrofitapi.response.DataResponse;

/**
 * Created by comp-1 on 3/2/17.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.MyViewHolder> {

    String json, placeid;
    String type1 = null;
    String type2 = null;
    DataResponse dataResponse;
    StringBuilder stringBuilder;
    ArrayList<String> arrayList = new ArrayList<>();;

    public DemoAdapter(String jsonString, String placeid) {
        json = jsonString;
        this.placeid = placeid;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView txtLongName, txtShortName, txtTypes;

        public MyViewHolder(View itemView) {
            super(itemView);

            txtLongName = (TextView) itemView.findViewById(R.id.txt_longname);
            txtShortName = (TextView) itemView.findViewById(R.id.txt_shortname);
            txtTypes = (TextView) itemView.findViewById(R.id.txt_types);

        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {



        arrayList.add(String.valueOf(dataResponse.getResults().get(0).getAddressComponents().get(position).getTypes().size()));

        stringBuilder = new StringBuilder();

        if (placeid.equals("ChIJ9yHfgj0DzYcRwkZr0WiJZKw"))
        {
            holder.txtLongName.setText(dataResponse.getResults().get(0).getAddressComponents().get(position).getLongName());

            holder.txtShortName.setText(dataResponse.getResults().get(0).getAddressComponents().get(position).getShortName());

            Log.d("size",String.valueOf(dataResponse.getResults().get(0).getAddressComponents().size()));

            for (int i = 0; i < Integer.parseInt(arrayList.get(position)); i++)
            {
                type1 = stringBuilder.append(" " + dataResponse.getResults().get(0).getAddressComponents().get(position).getTypes().get(i)).toString() ;
            }

            holder.txtTypes.setText(type1);
            stringBuilder.delete(0, stringBuilder.length());

        }

        if (placeid.equals("ChIJL273R381MYgRyg23f_bs1Nc"))
        {
            holder.txtLongName.setText(dataResponse.getResults().get(1).getAddressComponents().get(position).getLongName());

            holder.txtShortName.setText(dataResponse.getResults().get(1).getAddressComponents().get(position).getShortName());

            for (int i = 0; i < dataResponse.getResults().get(1).getAddressComponents().get(position).getTypes().size(); i++)
            {
                type2 = stringBuilder.append(" " + dataResponse.getResults().get(1).getAddressComponents().get(position).getTypes().get(i)).toString() ;
            }

            holder.txtTypes.setText(type2);

            stringBuilder.delete(0, stringBuilder.length());

        }

    }

    @Override
    public int getItemCount()
    {

        dataResponse = new DataResponse();

        dataResponse = new Gson().fromJson(json,DataResponse.class);

        int temp = 0;

        if (placeid.equals("ChIJ9yHfgj0DzYcRwkZr0WiJZKw"))
            temp = dataResponse.getResults().get(0).getAddressComponents().size();


        if (placeid.equals("ChIJL273R381MYgRyg23f_bs1Nc"))
            temp = dataResponse.getResults().get(1).getAddressComponents().size();;


        return temp;
    }





}
