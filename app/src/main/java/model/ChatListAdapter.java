package model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bcit.comp3717project.R;

import java.util.List;

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

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.chatlist_layout, null, true);

        TextView tvName = listViewItem.findViewById(R.id.textViewName);
        TextView tvMessage = listViewItem.findViewById(R.id.textViewMessage);
        TextView tvCreatedOn = listViewItem.findViewById(R.id.textViewCreatedOn);

        ChatMessage chatMessage = messageList.get(position);

        tvName.setText(chatMessage.getCreatedBy().getFirstName());
        tvMessage.setText(chatMessage.getMessage());
        tvCreatedOn.setText(chatMessage.getCreatedAt());

        return listViewItem;
    }
}
