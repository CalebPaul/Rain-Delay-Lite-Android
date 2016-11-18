package calebpaul.raindelay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SetConditionsActivity extends AppCompatActivity {
    @Bind(R.id.conditionName) EditText mConditionName;
    @Bind(R.id.windSpeed) EditText mMaxWindSpeed;
    @Bind(R.id.tempHi) EditText mMaxTemp;
    @Bind(R.id.tempLo) EditText mMinTemp;
    @Bind(R.id.submitConditionsButton) Button mSubmitConditionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_conditions);
        ButterKnife.bind(this);

        mSubmitConditionsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //String
                //Integer

                //Intent intent
                //intent.putExtra()

                //startActivity(intent)
            }
        });
    }
}
