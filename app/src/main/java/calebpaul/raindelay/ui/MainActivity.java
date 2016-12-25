package calebpaul.raindelay.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
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
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.Constants;
import calebpaul.raindelay.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private DatabaseReference mSearchedLocationReference;
    private LocationManager locationManager;
    private LocationListener listener;

    Animation animFadeIn, animSlideIn;

    @Bind(R.id.imageView) ImageView mBackgroundImageView;
    @Bind(R.id.mainTitleText) TextView mTitleTextView;
    @Bind(R.id.subtitleTextView) TextView mSubTitleTextView;
    @Bind(R.id.setWeatherButton) Button mSetConditionsButton;
    @Bind(R.id.viewForecastButton) Button mViewForecastButton;
    private String[] userLatLong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_USER_LATLONG);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_AUTO);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface Voltaire = Typeface.createFromAsset(getAssets(), "fonts/Voltaire-Regular.otf");
        mTitleTextView.setTypeface(Voltaire);

        userLatLong = new String[1];
        mSetConditionsButton.setOnClickListener(this);
        mViewForecastButton.setOnClickListener(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.v(TAG, "LAT, LONG: " + location.getLatitude() + ", " + location.getLongitude() );
                userLatLong[0] = (String.valueOf(location.getLatitude()+","+String.valueOf(location.getLongitude())));
                mSearchedLocationReference.child("userLatLong").setValue(String.valueOf(userLatLong[0]));
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };
        configure_button();
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        animSlideIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in);
    }

    public MainActivity fadeIn() {
        mSubTitleTextView.setVisibility(View.VISIBLE);
        mSubTitleTextView.startAnimation(animFadeIn);
        return this;
    }

    public void slideIn() {
        mTitleTextView.setVisibility(View.VISIBLE);
        mTitleTextView.startAnimation(animSlideIn);
    }

    @Override
    protected void onResume() {
        super.onResume();

        int currentDayNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        //currentDayNightMode value of 32 = Night, Value of 16 = Day.

        if (currentDayNightMode == 32) {
            //Dark Theme for Main Activity
            mBackgroundImageView.setImageResource(R.drawable.darkbackground);
            mBackgroundImageView.setAdjustViewBounds(true);
            mBackgroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mTitleTextView.setTextColor(Color.WHITE);
            mSubTitleTextView.setTextColor(Color.WHITE);
            mSetConditionsButton.setTextColor(Color.WHITE);
            mSetConditionsButton.setBackgroundColor(Color.DKGRAY);
            mViewForecastButton.setTextColor(Color.WHITE);
            mViewForecastButton.setBackgroundColor(Color.DKGRAY);

        } else {
            //Light Theme for Main Activity
            mBackgroundImageView.setImageResource(R.drawable.background);
            mBackgroundImageView.setAdjustViewBounds(true);
            mBackgroundImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        fadeIn();
    }

    private void configure_button() {
        // first check for permissions
        Log.v(TAG, "BOOLS FINE: " + String.valueOf(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED));
        Log.v(TAG, "BOOLS COARSE: " + String.valueOf(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED));
        Log.v(TAG, "BOOLS BUILD VERSION: " + String.valueOf(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                    ,10);
        }

        Log.v(TAG, "BOOLS LACK PERMISSIONS?: " + String.valueOf(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Log.v(TAG, "SECOND IF");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.v(TAG, "THIRD IF");
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
                        ,10);
            }
            return;
        }

        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
        mViewForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //noinspection MissingPermission
                locationManager.requestLocationUpdates("gps", 500, 25, listener);
                Intent intent = new Intent(MainActivity.this, ViewForecastActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "App needs GPS Permissions to function.  Please enable then restart App.", Toast.LENGTH_LONG);
                }
                return;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mSetConditionsButton) {
            Intent intent = new Intent(MainActivity.this, SetConditionsActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent =  new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
