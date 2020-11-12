package com.bcit.comp3717project;

import android.animation.ValueAnimator;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.EventListener;

import model.Church;

public class MapChurches extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private boolean map_displayed = true;

    public MapChurches() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map_churches, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView("Map");
    }


    private void initRecyclerView(String name) {

        RecyclerView mapRecycler = getView().findViewById(R.id.recyclerView);
        Button expandbutton = getView().findViewById(R.id.swipeButton);
        expandbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (MapChurches.this.map_displayed) {
                    FragmentManager fm = getChildFragmentManager();
                    fm.beginTransaction()
                            .hide(getChildFragmentManager().findFragmentById(R.id.googleMap))
                            .commit();
                    MapChurches.this.map_displayed = false;
                } else {
                    FragmentManager fm = getChildFragmentManager();
                    fm.beginTransaction()
                            .show(getChildFragmentManager().findFragmentById(R.id.googleMap))
                            .commit();
                    MapChurches.this.map_displayed = true;
                }

            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);
        mapFragment.getMapAsync(this);

        //TODO retrieve from firebase and then create these churches
        ArrayList<Church> churches = new ArrayList<>();

        Church c1 = new Church("123", "Willingdon Church", "4812 Willingdon Ave, Burnaby, BC V5G 3H6", "604-435-5544", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        Church c2 = new Church("234", "Renfrew Baptist Church", "1899 Renfrew St, Vancouver, BC V5M 3J1", "604-253-2089", "Christianity", "The church is located in a cosmopolitan area of Vancouver close to a variety of ethnic groups. To this end we seek to reach out to our local neighborhood to share the Gospel of Jesus Christ .. a message of central importance to every soul. We desire to share that Gospel message with you. Come visit us! ", null);
        Church c3 = new Church("345", "Pacific Grace MB Church Vancouver", "2855 E 1st Ave, Vancouver, BC V5M 1A9", "604-255-6199", "Christianity", "Pacific Grace MB Church （基督教頌恩堂） began as Pacific Grace Mission Chapel at 1587 Frances Street in Vancouver, with its first fellowship on 11 December 1963. By 1964, a congregation was formed, worshiping in English and German, with a few ethnic Chinese participating. It was determined to reach all nearby residents, including the Chinese in Chinatown.", null);
        Church c4 = new Church("456", "West Coast Christian Fellowship", "3198 Georgia Street East, Vancouver, BC V5K 2L1", "604-255-7301", "Christianity", "We're a Christian church in the Hastings-Sunrise neighbourhood of East Vancouver, just two blocks away from PNE Playland. We'd love you to come visit.", null);
        Church c5 = new Church("567", "Broadway Church", "2700 E Broadway, Vancouver, BC V5M 1Y8", "604-253-2700", "Christianity", "We exist to produce fully devoted followers of Jesus Christ. We desire to be a non-threatening community where one can both seek and grow in Christ.", null);
        Church c6 = new Church("678", "City Baptist Church", "2775 E Hastings St, Vancouver, BC V5K 1Z8", "604-562-0887", "Christianity", "We exist to share the life changing love of Jesus with Vancouver and beyond. We invite to worship with City Baptist Church in Vancouver. We are passionate about teaching the life changing truth of the Bible and sharing that truth in our community.", null);

        churches.add(c1);
        churches.add(c2);
        churches.add(c3);
        churches.add(c4);
        churches.add(c5);
        churches.add(c6);


        Church[] church_array = churches.toArray(new Church[churches.size()]);
        ChurchAdapter adapter = new ChurchAdapter(church_array); ///TODO add images?

        mapRecycler.setAdapter(adapter);

        GridLayoutManager lm = new GridLayoutManager(getView().getContext(), 1);

        mapRecycler.setLayoutManager(lm);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng vancouver = new LatLng(49.2827, -123.1207);
        mMap.addMarker(new MarkerOptions().position(vancouver).title("Marker in Vancouver"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vancouver));
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(14.0f);

    }

    private void initViews(View view) {


    }


}