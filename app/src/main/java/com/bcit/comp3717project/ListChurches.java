package com.bcit.comp3717project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import adapter.ChurchAdapter;
import model.Church;


public class ListChurches extends Fragment {

    public ListChurches() {
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
        return inflater.inflate(R.layout.fragment_list_churches, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initRecyclerView("List");
    }

    private void initRecyclerView(String name) {
        RecyclerView mapRecycler = getView().findViewById(R.id.recyclerView);

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
        ChurchAdapter adapter = new ChurchAdapter(church_array, true);

        mapRecycler.setAdapter(adapter);
        GridLayoutManager lm = new GridLayoutManager(getView().getContext(), 2);
        mapRecycler.setLayoutManager(lm);
    }
}
