package calebpaul.raindelay.services;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import calebpaul.raindelay.Constants;
import calebpaul.raindelay.models.Forecast;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by calebpaul on 12/3/16.
 */
public class DarkSkyService {
    public static final String TAG = DarkSkyService.class.getSimpleName();

    String latitude = "45.52";
    String longitude = "122.67";
    String userLatLong = latitude + "," + longitude;

    public static void getForecast(String userLatLong, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DARKSKY_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(Constants.API_KEY);
        urlBuilder.addPathSegment(userLatLong);
        String url = urlBuilder.build().toString();

        Log.v(TAG, "DARKSKY URL: " + url);
        Request request = new Request.Builder().url(url).build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Forecast> processForecast(Response response){
        ArrayList<Forecast> newForecasts = new ArrayList<>();

        try {
            String jsonData = response.body().string();

            if (response.isSuccessful()) {
                JSONObject forecasts = new JSONObject(jsonData);

                Log.v(TAG, "forecasts: " + forecasts);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newForecasts;
    }
}
