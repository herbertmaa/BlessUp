package adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.bcit.comp3717project.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;
import model.Church;

/**
 * Populates ListView for ChatChannelActivity. Each list item contains details
 * (an image, address and icons) that are associated with the Church chat channel that the
 * current user has joined
 */
public class ChatChannelAdapter extends ArrayAdapter<Church>{
    private Activity context;
    private List<Church> churchList;

    public ChatChannelAdapter(Activity context, ArrayList<Church> churchList) {
        super(context, R.layout.activity_chat_channel, churchList);
        this.context = context;
        this.churchList = churchList;
    }

    /**
     * Total count of Churches in Firebase
     * @return - the total number of churches
     */
    public int getCount() {
        return churchList.size();
    }

    /**
     * Populates each ListView item with an image to represent the Church, an address,
     * a string detailing the timestamp of when the last message in the channel was
     * received, and icons representing the number of unread messages
     */
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
        CircleImageView imageView = listViewItem.findViewById(R.id.church_icon_channel);

        churchNameText.setText(church.getName());
        churchAddressText.setText(church.getAddress());
        loadImageView(church.getImageURL(), church.getName(), imageView);

        if(getRandomNumber()<2){
            chatDateText.setTextColor(context.getColor(R.color.channel_messageNotification));
            chatNotifsText.setVisibility(View.VISIBLE);
        } else {
            chatNotifsText.setVisibility(View.INVISIBLE);
        }

        return listViewItem;
    }

    /**
     * Random integer to mock the case in which a user has received multiple
     * unviewed messages
     * @return a random integer between 0 & 5
     */
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

    /**
     * Loads the @param churchName's associated image from @url FirebaseStorage and sets loaded image to @param image
     * @param url - url the image stored in FireStore
     * @param churchName - the church this image is associated with
     * @param image - CircleImageView being set
     */
    private void loadImageView(String url, String churchName, CircleImageView image) {
        try {
            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(url);
            final File localFile = File.createTempFile(churchName, "png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                Resources res = context.getResources();
                                if (image != null) {
                                    image.setImageDrawable(RoundedBitmapDrawableFactory.create(res, bitmap));
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
