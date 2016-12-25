package calebpaul.raindelay.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.R;
import calebpaul.raindelay.models.Forecast;

/**
 * Created by calebpaul on 12/4/16.
 */
public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListAdapter.ForecastViewHolder> {
    private ArrayList<Forecast> mForecasts = new ArrayList<>();
    private Context mContext;

    public ForecastListAdapter(Context context, ArrayList<Forecast> forecasts) {
        mContext = context;
        mForecasts = forecasts;
    }

    @Override
    public ForecastListAdapter.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_list_item, parent, false);
        ForecastViewHolder viewHolder = new ForecastViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ForecastListAdapter.ForecastViewHolder holder, int position) {
        holder.bindForecast(mForecasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mForecasts.size();
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @Bind(R.id.timeTextView) TextView mTimeTextView;
        @Bind(R.id.summaryTextView) TextView mSummaryTextView;
        @Bind(R.id.temperatureTextView) TextView mTemperatureTextView;
        @Bind(R.id.windSpeedTextView) TextView mWindSpeedTextView;

        public ForecastViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindForecast(Forecast forecast) {
            mSummaryTextView.setText(forecast.getSummary());
            mTemperatureTextView.setText("Temp approx " + String.valueOf(forecast.getTemperature()) + " °F");

            if (forecast.getWindSpeed() == 0) {
                mWindSpeedTextView.setText("No Wind");
            } else {
                mWindSpeedTextView.setText("Winds up to " + String.valueOf(forecast.getWindSpeed()) + " MPH");
            }


            String forecastTime = convertUnixTime(forecast.getTime());
            mTimeTextView.setText(forecastTime);
//            Log.v("FORECAST LIST ADAPTER", String.valueOf(System.currentTimeMillis()) );

//            mTimeTextView.setText("Date: " + String.valueOf(forecast.getTime()));

            mSummaryTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://darksky.net"));
            mContext.startActivity(webIntent);
        }
    }

    private String convertUnixTime(long unixTime) {
        unixTime = unixTime * 1000;
        Date date = new Date(unixTime);
        DateFormat formatter = new SimpleDateFormat("EEE, MMM d, ''yy");
//        DateFormat formatter = DateFormat.getDateTimeInstance(date);
        String formattedTime = formatter.format(date);

        return formattedTime;
    }
}
