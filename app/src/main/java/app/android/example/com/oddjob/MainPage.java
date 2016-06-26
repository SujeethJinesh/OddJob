package app.android.example.com.oddjob;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by lab on 6/25/2016.
 */
public class MainPage extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        Profile profile = (Profile) getIntent().getSerializableExtra("profile");

        Button requestJobButton = (Button) findViewById(R.id.requestJobButton);
        Button findJobButton = (Button) findViewById(R.id.findJobButton);

        requestJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToRequestJobActivity();
            }
        });

        findJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInstantMessagingActivity();
            }
        });
    }

    private void goToRequestJobActivity() {
        Intent intent = new Intent(this, RequestJob.class);

        startActivity(intent);
    }

    private void goToInstantMessagingActivity() {
        Intent intent = new Intent(this, goToInstantMessagingActivity.class);

        startActivity(intent);
    }

}
