package adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bcit.comp3717project.ChurchDetailActivity;
import com.bcit.comp3717project.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;

import io.supercharge.shimmerlayout.ShimmerLayout;
import model.Church;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

/**
 * Controls how churches are loaded into the Church Card View
 */
public class ChurchAdapter extends FirebaseRecyclerAdapter<Church, ChurchAdapter.ViewHolder> {

    boolean loadImages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public ChurchAdapter(@NonNull FirebaseRecyclerOptions<Church> options, boolean loadImages) {
        super(options);
        this.loadImages = loadImages;
    }


    @NonNull
    @Override
    public ViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_church, parent, false);
        ShimmerLayout skeleton = (ShimmerLayout) cv.findViewById(R.id.shimmer_wrapper);
        skeleton.startShimmerAnimation();
        return new ViewHolder(cv);
    }


    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Church model) {
        final CardView cardView = holder.cardView;

        if (loadImages) {
            loadImageView(model.getImageURL(), model.getName(), cardView);
        } else {
            View imgView = cardView.findViewById(R.id.item_image);
            View shimmer = cardView.findViewById(R.id.shimmer_wrapper);
            shimmer.setVisibility(View.GONE);
            imgView.setVisibility(View.GONE);
        }

        TextView churchName = cardView.findViewById(R.id.church_name);
        churchName.setText(model.getName());

        TextView churchLocation = cardView.findViewById(R.id.church_location);
        churchLocation.setText(model.getAddress());


        cardView.setOnClickListener(v -> {
            Intent i = new Intent(cardView.getContext(), ChurchDetailActivity.class);
            i.putExtra("church", model);
            i.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            cardView.getContext().startActivity(i);
        });


    }


    private void loadImageView(String url, String churchName, CardView cv) {

        try {
            StorageReference mStorageReference = FirebaseStorage.getInstance().getReference().child(url);
            final File localFile = File.createTempFile(churchName, "png");
            mStorageReference.getFile(localFile)
                    .addOnSuccessListener(
                            taskSnapshot -> {
                                Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                ImageView imageView = (ImageView) cv.findViewById(R.id.item_image);

                                if (imageView != null) {
                                    imageView.setImageBitmap(bitmap);
                                    ShimmerLayout skeleton = (ShimmerLayout) cv.findViewById(R.id.shimmer_wrapper);
                                    skeleton.stopShimmerAnimation();
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
