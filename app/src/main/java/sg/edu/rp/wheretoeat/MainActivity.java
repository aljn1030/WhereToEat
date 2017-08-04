package sg.edu.rp.wheretoeat;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

    private static final AtomicInteger sCounter = new AtomicInteger(1);

    private ListView lvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Resources resources = getResources();// 获得res资源对象

        Configuration config = resources.getConfiguration();// 获得设置对象

        DisplayMetrics dm = resources.getDisplayMetrics();// 获得屏幕参数：主要是分辨率，像素等。

        String lang = PrefUtils.getLang(this);

        if (!lang.equals("")) {

            Locale locale = new Locale(lang);
            Locale.setDefault(locale);
            config.locale = locale;

            resources.updateConfiguration(config, dm);

        }

        lvResult = (ListView) findViewById(R.id.lv);
        showRestaurants();

        lvResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (parent == null || parent.getAdapter() == null ||
                        parent.getAdapter().getItem(position) == null ||
                        !(parent.getAdapter().getItem(position) instanceof Task)) {
                    return;
                }

                final Task task = (Task) parent.getAdapter().getItem(position);

                final Bundle bundle = new Bundle();
                bundle.putSerializable("task", task);

                final Intent intent = new Intent(MainActivity.this, StoreDetailActivity.class);
                intent.putExtras(bundle);

                MainActivity.this.startActivity(intent);
            }
        });



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.english) {

            PrefUtils.setLang(this, Language.LANG_EN);
            startActivity(new Intent(this, MainActivity.class));
            finish();

        } else if (id == R.id.chinese) {

            PrefUtils.setLang(this, Language.LANG_ZH);
            startActivity(new Intent(this, MainActivity.class));
            finish();



        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.options, menu);
        return true;


    }


    private void showRestaurants() {
        final DBHelper db = new DBHelper(MainActivity.this);

        //clear tasks
        db.clearTasks();

        //insert
        db.insertTask(new Task(
                (getString(R.string.Song_Fa_Bak_Kut_Teh)),
                new Location.Builder().lat(1.285343).lng(103.844737).build(),
                new Menu.Builder().append(getString(R.string.pork_ribs_soup)).append(getString(R.string.loin_ribs_soup)).append(getString(R.string.pig_liver_soup)).append(getString(R.string.pork_tenderloin)).append(getString(R.string.sliced_fish_soup)).append(getString(R.string.pig_stomach_soup)).append(getString(R.string.salted_vegetables)).build()));
        db.insertTask(new Task(
                (getString(R.string.tian_tian_chicken_rice)),
                new Location.Builder().lat(1.280518).lng(103.844808).build(),
                new Menu.Builder().append(getString(R.string.chicken_rice)).append(getString(R.string.half_chicken)).append(getString(R.string.rice)).append(getString(R.string.hainan_chicken_rice)).append(getString(R.string.liver)).append(getString(R.string.fried_rice)).build()));
        db.insertTask(new Task(
                (getString(R.string.sea_food_republic)),
                new Location.Builder().lat(1.257534).lng(103.822648).build(),
                new Menu.Builder().append(getString(R.string.chilli_crab)).append(getString(R.string.black_pepper_crab)).append(getString(R.string.white_pepper_crab)).append(getString(R.string.thai_style_curry_crab)).append(getString(R.string.sliced_fish_soup)).append(getString(R.string.salted_egg)).build()));
        db.insertTask(new Task(
                (getString(R.string.r4)),
                new Location.Builder().lat(1.280725).lng(103.850442).build(),
                new Menu.Builder().append(getString(R.string.r41)).append(getString(R.string.r42)).append(getString(R.string.r43)).append(getString(R.string.r44)).append(getString(R.string.r45)).append(getString(R.string.r46)).append(getString(R.string.r47)).append(getString(R.string.r48)).append(getString(R.string.r49)).append(getString(R.string.r410)).append(getString(R.string.r411)).append(getString(R.string.r412)).build()));
        db.insertTask(new Task(
                (getString(R.string.r5)),
                new Location.Builder().lat(1.317631).lng(103.852924).build(),
                new Menu.Builder().append(getString(R.string.pork_ribs_soup)).append(getString(R.string.loin_ribs_soup)).append(getString(R.string.pig_liver_soup)).append(getString(R.string.pork_tenderloin)).append(getString(R.string.sliced_fish_soup)).append(getString(R.string.pig_stomach_soup)).append(getString(R.string.salted_vegetables)).build()));
        db.insertTask(new Task(
                (getString(R.string.r6)),
                new Location.Builder().lat(1.280725).lng(103.850442).build(),
                new Menu.Builder().append(getString(R.string.r61)).append(getString(R.string.r62)).append(getString(R.string.r63)).append(getString(R.string.r64)).append(getString(R.string.r65)).append(getString(R.string.r66)).append(getString(R.string.r67)).append(getString(R.string.r68)).append(getString(R.string.r69)).append(getString(R.string.r610)).append(getString(R.string.r611)).append(getString(R.string.r612)).build()));
        db.insertTask(new Task(
                (getString(R.string.r7)),
                new Location.Builder().lat(1.311494).lng(103.856670).build(),
                new Menu.Builder().append(getString(R.string.r71)).append(getString(R.string.r72)).append(getString(R.string.r73)).append(getString(R.string.r74)).append(getString(R.string.r75)).append(getString(R.string.r76)).append(getString(R.string.r77)).append(getString(R.string.r78)).build()));
        db.insertTask(new Task(
                (getString(R.string.r8)),
                new Location.Builder().lat(1.280725).lng(103.850442).build(),
                new Menu.Builder().append(getString(R.string.chilli_crab)).append(getString(R.string.black_pepper_crab)).append(getString(R.string.white_pepper_crab)).append(getString(R.string.thai_style_curry_crab)).append(getString(R.string.sliced_fish_soup)).append(getString(R.string.salted_egg)).build()));
        db.insertTask(new Task(
                (getString(R.string.r9)),
                new Location.Builder().lat(1.280725).lng(103.831951).build(),
                new Menu.Builder().append(getString(R.string.r71)).append(getString(R.string.r72)).append(getString(R.string.r73)).append(getString(R.string.r74)).append(getString(R.string.r75)).append(getString(R.string.r76)).append(getString(R.string.r77)).append(getString(R.string.r78)).build()));
        db.insertTask(new Task(
                (getString(R.string.r10)),
                new Location.Builder().lat(1.312410).lng(103.837959).build(),
                new Menu.Builder().append(getString(R.string.r41)).append(getString(R.string.r42)).append(getString(R.string.r43)).append(getString(R.string.r44)).append(getString(R.string.r45)).append(getString(R.string.r46)).append(getString(R.string.r47)).append(getString(R.string.r48)).append(getString(R.string.r49)).append(getString(R.string.r410)).append(getString(R.string.r411)).append(getString(R.string.r412)).build()));
        db.insertTask(new Task(
                (getString(R.string.r11)),
                new Location.Builder().lat(1.280431).lng(103.844822).build(),
                new Menu.Builder().append(getString(R.string.r71)).append(getString(R.string.r72)).append(getString(R.string.r73)).append(getString(R.string.r74)).append(getString(R.string.r75)).append(getString(R.string.r76)).append(getString(R.string.r77)).append(getString(R.string.r78)).build()));



        //show
        final ArrayList<Task> tasks = db.getTasks();
        lvResult.setAdapter(new TaskArrayAdapter(MainActivity.this, R.layout.row, tasks));

        //close
        db.close();



    }










}



