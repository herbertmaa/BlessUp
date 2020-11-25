package com.bcit.comp3717project;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import model.Church;


/**
 * Controls how churches are loaded into the Church Card View
 */
public class ChurchAdapter extends RecyclerView.Adapter<ChurchAdapter.ViewHolder> {

    private Church[] churches;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public ChurchAdapter(Church[] churches) {
        this.churches = churches;
    }

    @NonNull
    @Override
    public ChurchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_church, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ChurchAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        //TODO add images to church adapter
//        ImageView imageView = cardView.findViewById(R.id.item_image);
//        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(),
//                imageIds[position]);
//        imageView.setImageDrawable(drawable);
//        imageView.setContentDescription(captions[position]);

        TextView churchName = cardView.findViewById(R.id.church_name);
        churchName.setText(churches[position].getName());


        TextView churchDescription = cardView.findViewById(R.id.church_description);
        churchDescription.setText(churches[position].getDescription());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cardView.getContext(), ChurchDetailActivity.class);
                i.putExtra("church", churches[position]);
                cardView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return churches.length;
    }


}
