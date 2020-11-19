package fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bcit.comp3717project.R;

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

        //TODO retrieve from firebase and then create these churches
        ArrayList<Church> churches = new ArrayList<>();

        Church c1 = new Church("123" , "Willingdon Church", "4812 Willingdon Ave, Burnaby", "604-435-5544", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c1.setImageURL("pictures/willingdon.png");
        Church c2 = new Church("234" , "Renfrew Baptist Church", "4812 Willingdon Ave, Burnaby", "604-253-2089", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c2.setImageURL("pictures/renfrew.png");
        Church c3 = new Church("345" , "Pacific Grace MB Church Vancouver", "4812 Willingdon Ave, Burnaby", "604-255-6199", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c3.setImageURL("pictures/pacific_grace.png");
        Church c4 = new Church("456" , "West Coast Christian Fellowship", "4812 Willingdon Ave, Burnaby", "604-255-7301", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c4.setImageURL("pictures/westcoast_fellowship.png");
        Church c5 = new Church("567" , "Broadway Church", "4812 Willingdon Ave, Burnaby", "604-253-2700", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c5.setImageURL("pictures/broadway_church.png");
        Church c6 = new Church("678" , "City Baptist Church", "4812 Willingdon Ave, Burnaby", "604-562-0887", "Christianity", "Willingdon Church is a community church; international in attendance, biblical in its message, uplifting in its worship and committed in its service to all ages ", null);
        c6.setImageURL("pictures/city_baptist.png");

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
}