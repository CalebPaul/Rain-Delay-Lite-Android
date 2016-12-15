package calebpaul.raindelay.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.Constants;
import calebpaul.raindelay.R;
import calebpaul.raindelay.adapters.ForecastListAdapter;
import calebpaul.raindelay.models.Forecast;
import calebpaul.raindelay.services.DarkSkyService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ViewForecastActivity extends AppCompatActivity {
    public static final String TAG = ViewForecastActivity.class.getSimpleName();

    private DatabaseReference mUserLocationReference;
    private ValueEventListener mCurrentUserLocationListener;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    private ForecastListAdapter mAdapter;

    public ArrayList<Forecast> mForecasts = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mUserLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_USER_LATLONG);

//        mCurrentUserLocationListener = mUserLocationReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                String userCurrentLatLong = dataSnapshot.child("userLatLong").getValue().toString();
//                Log.v(TAG, "DB RETRIEVE: " + userCurrentLatLong);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_forecast);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentLocation = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        //Log.d("Shared Pref Location", mRecentLocation);


        getForecasts();
    }

    private void getForecasts() {
        final DarkSkyService darkSkyService = new DarkSkyService();
        mCurrentUserLocationListener = mUserLocationReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userCurrentLatLong = dataSnapshot.child("userLatLong").getValue().toString();
                Log.v(TAG, "DB RETRIEVE + QUERY LAT/LONG: " + userCurrentLatLong);

                darkSkyService.getForecast(userCurrentLatLong, new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        mForecasts = darkSkyService.processForecast(response);

                        ViewForecastActivity.this.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                mAdapter = new ForecastListAdapter(getApplicationContext(), mForecasts);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewForecastActivity.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);
                            }
                        });
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        darkSkyService.getForecast(userCurrentLatLong, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                mForecasts = darkSkyService.processForecast(response);
//
//                ViewForecastActivity.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mAdapter = new ForecastListAdapter(getApplicationContext(), mForecasts);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewForecastActivity.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
    }
}
