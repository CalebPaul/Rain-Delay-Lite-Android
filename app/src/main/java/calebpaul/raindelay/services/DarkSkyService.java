package calebpaul.raindelay.services;

import org.json.JSONArray;
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
    //Will get location info from phone's gps in the future.

    public static void getForecast(String userLatLong, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DARKSKY_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(Constants.API_KEY);
        urlBuilder.addPathSegment(userLatLong);
        String url = urlBuilder.build().toString();

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
                JSONObject today = forecasts.getJSONObject("currently");
                JSONArray forecastsArray = forecasts.getJSONObject("daily").getJSONArray("data");

                int time = today.getInt("time");
                String summary = today.getString("summary");
                String icon = today.getString("icon");
                int temp = today.getInt("temperature");
                int rainProbability = today.getInt("precipProbability");
                int rainIntensity = today.getInt("precipIntensity");
                int windSpeed = today.getInt("windSpeed");

                Forecast currentForecast = new Forecast(time, summary, icon, temp, rainProbability, rainIntensity, windSpeed);
                newForecasts.add(currentForecast);

                for (int i = 0; i < forecastsArray.length(); i++) {
                    int dailyTime = forecastsArray.getJSONObject(i).getInt("time");
                    String dailySummary = forecastsArray.getJSONObject(i).getString("summary");
                    String dailyIcon = forecastsArray.getJSONObject(i).getString("icon");
                    int dailyTemp = (forecastsArray.getJSONObject(i).getInt("temperatureMin") + forecastsArray.getJSONObject(i).getInt("temperatureMax") / 2); // << This is not accurate at all, it's a workaround.
                    int dailyRainProbability = forecastsArray.getJSONObject(i).getInt("precipProbability");
                    int dailyRainIntensity = forecastsArray.getJSONObject(i).getInt("precipIntensity");
                    int dailyWindSpeed = forecastsArray.getJSONObject(i).getInt("windSpeed");

                    Forecast dailyForecast = new Forecast(dailyTime, dailySummary, dailyIcon, dailyTemp, dailyRainProbability, dailyRainIntensity, dailyWindSpeed);
                    newForecasts.add(dailyForecast);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newForecasts;
    }
}
