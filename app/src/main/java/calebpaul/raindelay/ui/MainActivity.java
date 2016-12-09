package calebpaul.raindelay.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.Constants;
import calebpaul.raindelay.R;
import calebpaul.raindelay.services.DarkSkyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedLocationReference;

    @Bind(R.id.mainTitleText) TextView mTitleText;
    @Bind(R.id.setWeatherButton) Button mSetConditionsButton;
    @Bind(R.id.viewForecastButton) Button mViewForecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LATLONG);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface Voltaire = Typeface.createFromAsset(getAssets(), "fonts/Voltaire-Regular.otf");
        mTitleText.setTypeface(Voltaire);

        mSetConditionsButton.setOnClickListener(this);
        mViewForecastButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mViewForecastButton) {
            final DarkSkyService newDarkSkyService = new DarkSkyService();
            String location = newDarkSkyService.getUserLatLong();
            Log.v(TAG, "ON CLICK: " + location);
            saveLocationToFirebase(location);

//            addToSharedPreferences(location);

            Intent intent = new Intent(MainActivity.this, ViewForecastActivity.class);
            startActivity(intent);
        }

        if (view == mSetConditionsButton) {
            Intent intent = new Intent(MainActivity.this, SetConditionsActivity.class);
            startActivity(intent);
        }
    }

    private void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

}
