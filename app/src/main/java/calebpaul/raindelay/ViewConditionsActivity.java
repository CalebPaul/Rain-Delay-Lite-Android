package calebpaul.raindelay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewConditionsActivity extends AppCompatActivity {
    @Bind(R.id.conditionNameTextView) TextView mConditionNameView;
    @Bind(R.id.maxWindTextView) TextView mWindView;
    @Bind(R.id.maxTempTextView) TextView mMaxTempView;
    @Bind(R.id.minTempTextView) TextView mMinTempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_conditions);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String windSpeed = String.valueOf(intent.getIntExtra ("windSpeed", 20));
        Log.v("ViewConditionsActivity", "onCreate: " + windSpeed);
        String maxTemp = intent.getStringExtra("maxTemp");
        String minTemp = intent.getStringExtra("minTemp");

        mConditionNameView.setText(name);
        mWindView.setText(windSpeed);
        mMaxTempView.setText(maxTemp);
        mMinTempView.setText(minTemp);
    }
}
