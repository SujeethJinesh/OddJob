package app.android.example.com.oddjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by lab on 6/25/2016.
 */
public class RequestJob extends AppCompatActivity {

    private ArrayList<String> skillsArrayList = new ArrayList<String>(0);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_job);

        Button submitButton = (Button) findViewById(R.id.requestNextButton);
        final Intent intent = new Intent(this, MapsActivity.class);

        if (submitButton != null) {
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (((CheckBox) findViewById(R.id.requestTutoringCheckBox)).isChecked()) {
                        skillsArrayList.add("tutoring");
                    }
                    if (((CheckBox) findViewById(R.id.requestBabysittingCheckBox)).isChecked()) {
                        skillsArrayList.add("babysitting");
                    }
                    if (((CheckBox) findViewById(R.id.requestCookingCheckBox)).isChecked()) {
                        skillsArrayList.add("cooking");
                    }
                    if (((CheckBox) findViewById(R.id.requestDrivingCheckBox)).isChecked()) {
                        skillsArrayList.add("driving");
                    }
                    if (((CheckBox) findViewById(R.id.requestLiftingCheckBox)).isChecked()) {
                        skillsArrayList.add("lifting");
                    }

                    String[] skills = new String[skillsArrayList.size()];
                    skillsArrayList.toArray(skills);

                    intent.putExtra("skills", skills);

                    startActivity(intent);
                }
            });
        }

    }

}
