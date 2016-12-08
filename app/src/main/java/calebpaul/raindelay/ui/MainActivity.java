package calebpaul.raindelay.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Bind(R.id.mainTitleText) TextView mTitleText;
    @Bind(R.id.setWeatherButton) Button mConditionsButton;
    @Bind(R.id.viewForecastButton) Button mViewForecastButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface Voltaire = Typeface.createFromAsset(getAssets(), "fonts/Voltaire-Regular.otf");
        mTitleText.setTypeface(Voltaire);

        mConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SetConditionsActivity.class);
                startActivity(intent);
            }
        });

//        mViewForecastButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ViewForecastActivity.class);
//                startActivity(intent);
//            }
//        });

        mViewForecastButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == mViewForecastButton) {
            //String location = mLocationEditText.getText().toSTring();
        }
    }
}
