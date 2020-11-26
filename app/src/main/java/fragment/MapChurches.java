package fragment;

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

import com.bcit.comp3717project.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.FirebaseDatabase;

import adapter.ChurchAdapter;
import model.Church;

public class MapChurches extends Fragment implements OnMapReadyCallback {

    private ChurchAdapter adapter;
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


        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Church> options
                = new FirebaseRecyclerOptions.Builder<Church>()
                .setQuery(FirebaseDatabase.getInstance().getReference("churches"), Church.class)
                .build();

        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new ChurchAdapter(options, true);

        mapRecycler.setAdapter(adapter);
        GridLayoutManager lm = new GridLayoutManager(getView().getContext(), 1);
        mapRecycler.setLayoutManager(lm);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        float zoomLevel = 18.0f;
        LatLng vancouver = new LatLng(49.2526718, -123.0659625);
        mMap.addMarker(new MarkerOptions().position(vancouver).title("Marker in Vancouver"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(vancouver, zoomLevel));
        mMap.setMinZoomPreference(6.0f);
        mMap.setMaxZoomPreference(14.0f);

        //Willingdon Church marker
        LatLng c1 = new LatLng(49.2410393, -123.0049571);
        mMap.addMarker(new MarkerOptions().position(c1).title("Willingdon Church"));

        //Renfrew Baptist Church marker
        LatLng c2 = new LatLng(49.2678501, -123.0445683);
        mMap.addMarker(new MarkerOptions().position(c2).title("Renfrew Baptist Church"));

        //Pacific Grace MB Church Vancouver
        LatLng c3 = new LatLng(49.2522421, -123.1040729);
        mMap.addMarker(new MarkerOptions().position(c3).title("Pacific Grace MB Church Vancouver"));

        //West Coast Christian Fellowship
        LatLng c4 = new LatLng(49.2698328, -123.047887);
        mMap.addMarker(new MarkerOptions().position(c4).title("West Coast Christian Fellowship"));

        //Broadway Church
        LatLng c5 = new LatLng(49.2617278, -123.0489528);
        mMap.addMarker(new MarkerOptions().position(c5).title("Broadway Church"));

        //City Baptist Church
        LatLng c6 = new LatLng(49.2813891, -123.0496031);
        mMap.addMarker(new MarkerOptions().position(c6).title("City Baptist Church"));

    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}