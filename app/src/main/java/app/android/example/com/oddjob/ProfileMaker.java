package app.android.example.com.oddjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by jocelyn on 6/25/16.
 */
public class ProfileMaker extends AppCompatActivity {

    private ArrayList<String> skillsArrayList = new ArrayList<String>(0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_maker_layout);

        final String name = ((EditText) findViewById(R.id.nameEditText)).toString();

        Button submitButton = (Button) findViewById(R.id.submitButton);
        final Intent intent = new Intent(this, FindingJobMapsActivity.class);

        if (submitButton != null) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (((CheckBox) findViewById(R.id.tutoringCheckBox)).isChecked()) {
                        skillsArrayList.add("tutoring");
                    }
                    if (((CheckBox) findViewById(R.id.babysittingCheckBox)).isChecked()) {
                        skillsArrayList.add("babysitting");
                    }
                    if (((CheckBox) findViewById(R.id.cookingCheckBox)).isChecked()) {
                        skillsArrayList.add("cooking");
                    }
                    if (((CheckBox) findViewById(R.id.drivingCheckBox)).isChecked()) {
                        skillsArrayList.add("driving");
                    }
                    if (((CheckBox) findViewById(R.id.liftingCheckBox)).isChecked()) {
                        skillsArrayList.add("lifting");
                    }

                    String[] skills = new String[skillsArrayList.size()];
                    skillsArrayList.toArray(skills);

                    Profile profile = new Profile(name, skills);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("profile", profile);

                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            });
        }

    }

}
