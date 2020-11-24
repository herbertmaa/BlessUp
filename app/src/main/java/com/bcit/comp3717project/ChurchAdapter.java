package com.bcit.comp3717project;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

        TextView churchName = cardView.findViewById(R.id.church_name);
        churchName.setText(churches[position].getName());

        TextView churchDescription = cardView.findViewById(R.id.church_description);
        churchDescription.setText(churches[position].getDescription());

        cardView.setOnClickListener(v -> {
            Intent i = new Intent(cardView.getContext(), ChurchDetailActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            i.putExtra("church", churches[position]);
            cardView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {
        return churches.length;
    }


}
