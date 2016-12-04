package calebpaul.raindelay.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import calebpaul.raindelay.R;
import calebpaul.raindelay.models.Forecast;
import calebpaul.raindelay.services.DarkSkyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ViewForecastActivity extends AppCompatActivity {
    public static final String TAG = ViewForecastActivity.class.getSimpleName();

    public ArrayList<Forecast> mForecasts = new ArrayList<>();
    String latitude = "45.52";
    String longitude = "122.67";
    String userLatLong = latitude + "," + longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "IN ON CREATE");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_forecast);

        getForecasts();
    }

    private void getForecasts() {
        Log.v(TAG, "IN GET FORECASTS");
        final DarkSkyService darkSkyService = new DarkSkyService();
        darkSkyService.getForecast(userLatLong, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.v(TAG, "IN ON FAILURE");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v(TAG, "IN ON RESPONSE");
                mForecasts = darkSkyService.processForecast(response);
            }
        });
    }
}
