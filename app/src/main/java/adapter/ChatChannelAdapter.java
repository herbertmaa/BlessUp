package adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bcit.comp3717project.ChatChannelActivity;
import com.bcit.comp3717project.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.ChatMessage;
import model.Church;


public class ChatChannelAdapter extends ArrayAdapter<Church>{
    private Activity context;
    private List<Church> churchList;

    public ChatChannelAdapter(Activity context, ArrayList<Church> churchList) {
        super(context, R.layout.activity_chat_channels, churchList);
        this.context = context;
        this.churchList = churchList;
    }

    public int getCount() {
        return churchList.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getLayoutInflater();

        Church church = churchList.get(position);
        View listViewItem = inflater.inflate(R.layout.chat_channel_list_item, null, true);

        TextView churchNameText = (TextView) listViewItem.findViewById(R.id.church_name_channel);
        TextView churchAddressText = (TextView) listViewItem.findViewById(R.id.church_address_channel);
        TextView chatDateText = (TextView) listViewItem.findViewById(R.id.channel_chat_date_txt);
        TextView chatNotifsText = (TextView) listViewItem.findViewById(R.id.chat_notifs_txt);
        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.church_icon_channel);

        churchNameText.setText(church.getName());
        churchAddressText.setText(church.getAddress());
//        chatDateText.setText(church.get(position));

        if(getRandomNumber()<2){
            chatDateText.setTextColor(context.getColor(R.color.channel_messageNotification));
            chatNotifsText.setVisibility(View.VISIBLE);
        } else {
            chatNotifsText.setVisibility(View.INVISIBLE);
        }

        // change the icon for Windows and iPhone
        if(position==4){
            churchAddressText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
        }
        if(position==5){
            churchAddressText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_photo_camera, 0, 0, 0);
        }
        return listViewItem;
    }

    private int getRandomNumber() {
        Random random = new Random();
        int x = random.nextInt(5);
        if(x==0){
            return 0;
        }else if(x==1){
            return 1;
        }
        else if(x==2){
            return 2;
        }else
            return 3;

    }
}

