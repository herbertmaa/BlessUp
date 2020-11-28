package adapter;

import android.app.Activity;
import android.content.Context;
import android.text.Layout;
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

/**
 * Populates the Chat ListView with items containing message details pulled from ChatMessages
 */
public class ChatListAdapter extends ArrayAdapter<ChatMessage> {
    /**
     * The context of the currently executing activity
     */
    private Activity context;

    /**
     * List containing ChatMessages that will be processed into ListView items
     */
    private List<ChatMessage> messageList;

    /**
     * Constructor to set messageList and context for this module
     * @param context
     * @param messageList
     */
    public ChatListAdapter(Activity context, List<ChatMessage> messageList) {
        super(context, R.layout.activity_chat, messageList);
        this.context = context;
        this.messageList = messageList;
    }

    /**
     * Returns the total number of messages in the messageList
     * @return
     */
    public int getCount() {
        return messageList.size();
    }

    /**
     * Formats the listview item to contain details about the current message being processed.
     * Each message is rendered onto a my_message layout or their_message layout. The layout
     * is populated with a time stamp, owner's name, and message string
     */
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

    /**
     * Formats the date object to return the time in HH:MM format
     * @param date - Date object associated with the current message being processed
     * @return - time stamp in format HH:MM
     */
    private String formatTime(Date date) {
        String delegate = "hh:mm aaa";
        String formattedTime = (String) DateFormat.format(delegate, date);
        return formattedTime.charAt(0) == '0' ? formattedTime.substring(1) : formattedTime;
    }

    /**
     * Determines if @param chatMessage belongs to current user or to other user.
     * @param chatMessage - chatMessage being processed
     * @return - true if chatMessage belongs to this user, else false
     */
    public boolean belongsToCurrentUser(ChatMessage chatMessage) {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = auth.getCurrentUser();
        return chatMessage.getCreatedBy().getId().equals(firebaseUser.getUid());
    }
}
