/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.CommuterGames;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the messages data.
 */
class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<Message> mMessageData;
    private Context mContext;


    /**
     * Constructor that passes in the sports data and the context.
     *
     * @param messageData ArrayList containing the message data.
     * @param context Context of the application.
     */
    MessageAdapter(Context context, ArrayList<Message> messageData) {
        this.mMessageData = messageData;
        this.mContext = context;
    }



    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
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
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */
    @Override
    public void onBindViewHolder(MessageAdapter.ViewHolder holder, int position) {
        // Get current sport.
        Message currentMessage = mMessageData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentMessage);
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
        void bindTo(Message currentMessage){

            // Populate the textviews with data.
            mUsername.setText(currentMessage.getUsername());
            mMessage.setText(currentMessage.getText());

        }

    }
}

