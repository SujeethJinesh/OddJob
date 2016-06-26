package app.android.example.com.oddjob;

import java.io.Serializable;

/**
 * Created by lab on 6/25/2016.
 */
public class Profile implements Serializable {

    private String name;
    private String[] skills;

    public Profile (String name, String[] skills) {
        this.name = name;
        this.skills = skills;
    }

    public String getName() {
        return this.name;
    }

    public String[] getSkills() {
        return this.skills;
    }

}
