package com.bcit.comp3717project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.ChurchAdapter;
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

        ArrayList<Church> churches = new ArrayList<>();
        DatabaseReference churchesReference = FirebaseDatabase.getInstance().getReference("churches");
        churchesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot churchSnapshot: snapshot.getChildren()) {
                    Church church = churchSnapshot.getValue(Church.class);
                    churches.add(church);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        Church[] church_array = churches.toArray(new Church[churches.size()]);
        ChurchAdapter adapter = new ChurchAdapter(church_array);

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