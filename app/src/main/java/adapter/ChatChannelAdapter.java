package adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bcit.comp3717project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import io.supercharge.shimmerlayout.ShimmerLayout;
import model.Church;
import model.User;


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

        TextView churchNameText = listViewItem.findViewById(R.id.church_name_channel);
        TextView churchAddressText = listViewItem.findViewById(R.id.church_address_channel);
        TextView chatDateText = listViewItem.findViewById(R.id.channel_chat_date_txt);
        TextView chatNotifsText = listViewItem.findViewById(R.id.chat_notifs_txt);
        ImageView imageView = listViewItem.findViewById(R.id.church_icon_channel);

        churchNameText.setText(church.getName());
        churchAddressText.setText(church.getAddress());
        loadImageView(church.getImageURL(), church.getName(), imageView);

        if(getRandomNumber()<2){
            chatDateText.setTextColor(context.getColor(R.color.channel_messageNotification));
            chatNotifsText.setVisibility(View.VISIBLE);
        } else {
            chatNotifsText.setVisibility(View.INVISIBLE);
        }

        // add in photo media icon to some channels for visual appeal
        if(position==4){
            churchAddressText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_check, 0, 0, 0);
        }

        if(position==5){
            churchAddressText.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_photo_camera, 0, 0, 0);
        }

        // Check if Current User is Member of Church, Set Invisible if Not.
        HashMap<String, User> churchMembers = church.getMembers();
        FirebaseAuth auth = FirebaseAuth.getInstance();

        if (!churchMembers.containsKey(auth.getCurrentUser().getUid())) {
            System.out.println("NOT A MEMBER OF THIS CHURCH");
            listViewItem.setVisibility(View.GONE);
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

    private void loadImageView(String url, String churchName, ImageView v) {
        try {
            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(url);
            final File localFile = File.createTempFile(churchName, "png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());

                                if (v != null) {
                                    v.setImageBitmap(bitmap);
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getLastChatMessageBody() {
        //TODO
    }
}

