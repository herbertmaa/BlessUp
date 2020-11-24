package adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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

        ShimmerLayout skeleton = (ShimmerLayout) cv.findViewById(R.id.shimmer_wrapper);
        skeleton.startShimmerAnimation();

        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ChurchAdapter.ViewHolder holder, int position) {
        final CardView cardView = holder.cardView;

        Church currentChurch = churches[position];

        loadImageView(currentChurch.getImageURL(), currentChurch.getName(), cardView);

        TextView churchName = cardView.findViewById(R.id.church_name);
        churchName.setText(currentChurch.getName());

        TextView churchLocation = cardView.findViewById(R.id.church_location);
        churchLocation.setText(currentChurch.getAddress());


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(cardView.getContext(), ChurchDetailActivity.class);
                i.putExtra("church", currentChurch);
                cardView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return churches.length;
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
