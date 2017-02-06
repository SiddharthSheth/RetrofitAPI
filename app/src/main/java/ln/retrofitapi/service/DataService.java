package ln.retrofitapi.service;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by comp-1 on 3/2/17.
 */

public interface DataService {

    @GET("maps/api/geocode/json?address=3rd&amp;Lindsley&sensor=false")
    Call<JsonObject> getResult();

}
