package calebpaul.raindelay;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ExampleConditionsActivity extends AppCompatActivity implements ExampleListFragment.OnFragmentInteractionListener {
    @Bind(R.id.examplesListView) ListView mExamplesListView;

    String[] examples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example_conditions);
        ButterKnife.bind(this);

        examples = getResources().getStringArray(R.array.rider_types);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, examples);
        mExamplesListView.setAdapter(adapter);


        mExamplesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogFragment newExample = new ExampleListFragment();
                Bundle args =  new Bundle();
                args.putString("exampleName", parent.getItemAtPosition(position).toString());
                newExample.setArguments(args);
                newExample.show(getSupportFragmentManager(), "example");
            }
        });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
