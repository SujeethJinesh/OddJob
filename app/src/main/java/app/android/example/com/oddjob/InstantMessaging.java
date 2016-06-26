package app.android.example.com.oddjob;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by lab on 6/25/2016.
 */
public class InstantMessaging extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instant_messaging);

        String skill = getIntent().getStringExtra("markertitle");
        TextView personSkillTextView = (TextView) findViewById(R.id.personSkill);

        personSkillTextView.setText(skill);
    }

}
