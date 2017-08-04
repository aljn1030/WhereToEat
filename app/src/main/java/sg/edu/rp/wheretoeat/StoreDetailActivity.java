package sg.edu.rp.wheretoeat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class StoreDetailActivity extends AppCompatActivity {

    private TextView tvStoreName;
    private ListView menuList;
    private SupportMapFragment mapFragment;

    private Task task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_detail_layout);

        tvStoreName = (TextView) findViewById(R.id.tv_store);
        menuList = (ListView) findViewById(R.id.list_view);
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                if(task.location != null){
                    LatLng place = new LatLng(task.location.lat,task.location.lng);
                    googleMap.addMarker(new MarkerOptions().position(place).title(""));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(place));
                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
                }
            }
        });

        final Bundle bundle = getIntent().getExtras();

        if(bundle.getSerializable("task") != null){
            task = (Task) bundle.getSerializable("task");
        }

        if(task == null){
            finish();
        }

        tvStoreName.setText(task.description);
        if(task.menu.list != null){
            menuList.setAdapter(new MenuListAdapter(this, R.layout.menu_item, task.menu.list));
        }
    }



}
