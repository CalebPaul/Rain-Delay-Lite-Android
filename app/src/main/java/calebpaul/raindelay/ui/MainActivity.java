package calebpaul.raindelay.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.R;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.mainTitleText) TextView mTitleText;
    @Bind(R.id.setWeatherButton) Button mConditionsButton;
    @Bind(R.id.viewForecastButton) Button mViewForecastButton;

    //private Button b;
    private TextView t;
    private LocationManager locationManager;
    private LocationListener listener;

    //LocationService userLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                t.append("\n " + location.getLongitude() + " " + location.getLatitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };

        configure_button();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                configure_button();
                break;
            default:
                break;
        }
    }

    void configure_button() {
        // first check for permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        mViewForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 5000, 0, listener);
            }
        });




        Typeface Voltaire = Typeface.createFromAsset(getAssets(), "fonts/Voltaire-Regular.otf");
        mTitleText.setTypeface(Voltaire);


        mConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetConditionsActivity.class);
                startActivity(intent);
            }
        });

        mViewForecastButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

//                userLocation = new LocationService(MainActivity.this);
//
//                String stringLat = null;
//                String stringLong = null;
//                if (userLocation.canGetLocation()) {
//                    Log.v("MainActivity", "IN IF LOCATION");
//                    double latitude = userLocation.getLatitude();
//                    double longitude = userLocation.getLongitude();
//                    Log.v("MainActivity", "LAT: " + String.valueOf(latitude));
//                    Log.v("MainActivity", "LONG: " + String.valueOf(longitude));
//
//                    Toast.makeText(MainActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
//                } else {
//                    userLocation.showSettingsAlert();
//                }


            }
        });



    }
}
