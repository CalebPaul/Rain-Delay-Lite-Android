package calebpaul.raindelay.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.R;

public class ViewUserConditionsActivity extends AppCompatActivity {
    @Bind(R.id.conditionNameTextView) TextView mConditionNameView;
    @Bind(R.id.maxWindTextView) TextView mWindView;
    @Bind(R.id.maxTempTextView) TextView mMaxTempView;
    @Bind(R.id.minTempTextView) TextView mMinTempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_conditions);
        ButterKnife.bind(this);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String windSpeed = String.valueOf(intent.getIntExtra ("windSpeed", 20));
        String maxTemp = String.valueOf(intent.getIntExtra("maxTemp", 32));
        String minTemp = String.valueOf(intent.getIntExtra("minTemp", 8));

        mConditionNameView.setText("Ride Type: "+name);
        mWindView.setText("Max Wind Speed: "+windSpeed);
        mMaxTempView.setText("Max Temp (celsius): "+maxTemp);
        mMinTempView.setText("Min Temp (celsius): "+minTemp);
    }
}
