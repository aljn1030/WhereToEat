package sg.edu.rp.wheretoeat;

import java.io.Serializable;

/**
 * Created by 15017185 on 12/5/2017.
 */

public class Task implements Serializable{
    public int id;
    public String description;
    public Location location;
    public Menu menu;

    public Task(int id, String description, Location location, Menu menu) {
        this.id = id;
        this.description = description;
        this.location = location;
        this.menu = menu;
    }

    public Task(String description, Location location, Menu menu) {
        this.description = description;
        this.location = location;
        this.menu = menu;
    }

}
