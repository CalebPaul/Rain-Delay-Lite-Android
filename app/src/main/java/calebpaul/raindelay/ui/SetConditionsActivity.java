package calebpaul.raindelay.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import calebpaul.raindelay.R;

public class SetConditionsActivity extends AppCompatActivity {
    @Bind(R.id.conditionName) EditText mConditionName;
    @Bind(R.id.windSpeed) EditText mMaxWindSpeed;
    @Bind(R.id.tempHi) EditText mMaxTemp;
    @Bind(R.id.tempLo) EditText mMinTemp;
    @Bind(R.id.submitConditionsButton) Button mSubmitConditionsButton;
    @Bind(R.id.exampleConditionsButton) Button mExampleConditionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_conditions);
        ButterKnife.bind(this);

        mSubmitConditionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                /*Validate Fields by getting text, converting to string, trimming whitespace chars, and evaluating length.
                 * Adds toast to prompt user to input values. */

                if (mConditionName.getText().toString().trim().length() == 0) {
                    Toast.makeText(SetConditionsActivity.this, "Please enter a name.", Toast.LENGTH_SHORT).show();
                    return;

                } else if (mMaxWindSpeed.getText().toString().trim().length() == 0) {
                    Toast.makeText(SetConditionsActivity.this, "Please enter max wind speed.", Toast.LENGTH_SHORT).show();
                    return;

                } else if ((mMaxTemp.getText().toString().trim().length() == 0)) {

                    Toast.makeText(SetConditionsActivity.this, "Please enter max temp.", Toast.LENGTH_SHORT).show();
                    return;

                } else if ((mMaxTemp.getText().toString().trim().length() == 0)) {

                    Toast.makeText(SetConditionsActivity.this, "Please enter min temp.", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    String name = mConditionName.getText().toString();
                    Integer windSpeed = Integer.parseInt(String.valueOf(mMaxWindSpeed.getText()));
                    Integer maxTemp = Integer.parseInt(String.valueOf(mMaxTemp.getText()));
                    Integer minTemp = Integer.parseInt(String.valueOf(mMinTemp.getText()));

                    Intent intent = new Intent(SetConditionsActivity.this, ViewUserConditionsActivity.class);

                    intent.putExtra("name", name);
                    intent.putExtra("windSpeed", windSpeed);
                    intent.putExtra("maxTemp", maxTemp);
                    intent.putExtra("minTemp", minTemp);

                    startActivity(intent);
                }
            }
        });

        mExampleConditionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SetConditionsActivity.this, ExampleConditionsActivity.class);
                startActivity(intent);
            }
        });
    }
}
