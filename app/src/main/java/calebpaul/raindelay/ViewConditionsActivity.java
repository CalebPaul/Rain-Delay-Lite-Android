package calebpaul.raindelay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ViewConditionsActivity extends AppCompatActivity {
    @Bind(R.id.displayConditionName) TextView mConditionNameView;
    @Bind(R.id.maxWindTextView) TextView mWindView;
    @Bind(R.id.minTempTextView) TextView mMinTempView;
    @Bind(R.id.maxTempTextView) TextView mMaxTempView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_conditions);
        ButterKnife.bind(this);
    }
}
