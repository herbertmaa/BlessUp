package adapter;

import android.app.Activity;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bcit.comp3717project.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Date;
import java.util.List;

import model.ChatMessage;

public class ChatListAdapter extends ArrayAdapter<ChatMessage> {
    private Activity context;
    private List<ChatMessage> messageList;

    public ChatListAdapter(Activity context, List<ChatMessage> messageList) {
        super(context, R.layout.activity_chat, messageList);
        this.context = context;
        this.messageList = messageList;
    }

    public ChatListAdapter(Context context, int resource, List<ChatMessage> objects, Activity context1, List<ChatMessage> messageList) {
        super(context, resource, objects);
        this.context = context1;
        this.messageList = messageList;
    }

    public int getCount() {
        return messageList.size();
    }

    public ChatMessage getChatMessageItem(int i) {
        return messageList.get(i);
    }

    public long getChatMessageItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        ChatMessage chatMessage = messageList.get(position);
        View listViewItem;

        if (belongsToCurrentUser(chatMessage)) {
            listViewItem = inflater.inflate(R.layout.my_message, null, true);
        } else {
            listViewItem = inflater.inflate(R.layout.their_message, null, true);
            TextView tvName = listViewItem.findViewById(R.id.textViewName);
            tvName.setText(chatMessage.getCreatedBy().getFirstName());
        }

        TextView tvMessage = listViewItem.findViewById(R.id.textViewMessage);
        TextView tvCreatedOn = listViewItem.findViewById(R.id.textViewCreatedOn);

        tvMessage.setText(chatMessage.getMessage());
        tvCreatedOn.setText(formatTime(chatMessage.getCreatedAt()));

        return listViewItem;
    }

    private String formatTime(Date date) {
        String delegate = "hh:mm aaa";
        String formattedTime = (String) DateFormat.format(delegate, date);
        return formattedTime.charAt(0) == '0' ? formattedTime.substring(1) : formattedTime;
    }

    public boolean belongsToCurrentUser(ChatMessage chatMessage) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        return chatMessage.getCreatedBy().getId().equals(firebaseUser.getUid());
    }
}
