package com.example.ds.fid_me;

import android.app.FragmentManager;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class RecommandMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap = null;
    List<Address> address;
    Intent intent;
    SQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand_map);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment)fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        intent = getIntent();

        dbHelper = new SQLiteHelper(this);

    }

    @Override
    public void onMapReady(final GoogleMap map) {

        mGoogleMap = map;

        String  name = "";
        String addr = "";

        Bundle extras = getIntent().getExtras();

        name = extras.getString("name");
        addr = extras.getString("address");

        Geocoder mCoder = new Geocoder(this);
        try {
            //주소값을 통하여 로케일을 받아온다
            address = mCoder.getFromLocationName(addr, 1);
            Double Lat =  address.get(0).getLatitude();
            Double Lon =  address.get(0).getLongitude();
            //해당 로케일로 좌표를 구성한다
            LatLng location = new LatLng(Lat, Lon);

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(location);
            markerOptions.title(name);
            markerOptions.snippet(addr);
            map.addMarker(markerOptions);

            map.moveCamera(CameraUpdateFactory.newLatLng(location));
            map.animateCamera(CameraUpdateFactory.zoomTo(12));
        } catch (Exception e) {
            return;
        }

        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {

                String from = intent.getStringExtra("from");

                if (from.equals("home")) {

                    String title = marker.getTitle();
                    String address = marker.getSnippet();


                    Log.d("sql","go to dbHelper");
                    dbHelper.addData("HISTORY","",title, address,"-1","false");

                    Toast.makeText(getApplicationContext(),"history에 저장되었습니다.", Toast.LENGTH_LONG).show();



                }



                Intent intent = new Intent(getBaseContext(), HistoryActivity.class);

                String title = marker.getTitle();
                intent.putExtra("title", title);

                startActivity(intent);
            }
        });

    }

}