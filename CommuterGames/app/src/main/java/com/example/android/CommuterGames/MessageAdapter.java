/**
 * The code in the Adapter classes are based of
 * the code we used on the sport-list-app when
 * we learned about RecycleView
 */

package com.example.android.CommuterGames;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.CommuterGames.Message.Message;
import com.example.android.CommuterGames.Message.MessageController;
import com.example.android.CommuterGames.Message.MessageView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the messages data.
 */
class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Message> mMessageData;
    private Context mContext;

    /**
     * Constructor that passes in the message data and the context.
     *
     * @param messageData ArrayList containing the message data.
     * @param context     Context of the application.
     */
    MessageAdapter(Context context, ArrayList<Message> messageData) {
        this.mMessageData = messageData;
        this.mContext = context;
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent   The ViewGroup into which the new View will be added
     *                 after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.message_self_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder   The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {

        // Collects current Message.
        Message currentMessage = mMessageData.get(position);

        // Creates the MessageController for the Message
        MessageView messageView = new MessageView();
        MessageController controller = new MessageController(currentMessage, messageView);

        // Populate the TextViews with data, sends in the MessageController.
        holder.bindTo(controller);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */
    @Override
    public int getItemCount() {
        return mMessageData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mMessage;
        private TextView mUsername;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the game_list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mUsername = itemView.findViewById(R.id.username);
            mMessage = itemView.findViewById(R.id.message);

        }

        // Sets the values where they should be
        void bindTo(MessageController controller) {

            // Populates the TextViews with Strings
            mUsername.setText(controller.getMessageUsername());
            mMessage.setText(controller.getMessageText());
        }
    }
}

