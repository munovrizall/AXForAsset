package com.artonov.axforasset.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.artonov.axforasset.DetailActivity;
import com.artonov.axforasset.R;
import com.artonov.axforasset.model.GameAsset;

import java.util.List;

public class GameAssetAdapter extends RecyclerView.Adapter<GameAssetAdapter.ViewHolder> {
    private List<GameAsset> gameAssetList;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView gameImageView;
        public TextView titleTextView;
        public TextView descTextView;

        public ViewHolder(View view) {
            super(view);
            gameImageView = view.findViewById(R.id.ivGame);
            titleTextView = view.findViewById(R.id.tvGame);
            descTextView = view.findViewById(R.id.tvDesc);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            GameAsset gameAsset = gameAssetList.get(position);

            // Mengarahkan pengguna ke DetailActivity dan mengirimkan data game asset
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", gameAsset.getTitle());
            intent.putExtra("description", gameAsset.getDescription());
            intent.putExtra("image", gameAsset.getImageResId());
            context.startActivity(intent);
        }
    }

    public GameAssetAdapter(List<GameAsset> gameAssetList, Context context) {
        this.gameAssetList = gameAssetList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game_asset, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GameAsset gameAsset = gameAssetList.get(position);
        holder.titleTextView.setText(gameAsset.getTitle());
        holder.descTextView.setText(gameAsset.getDescription());
        holder.gameImageView.setImageResource(gameAsset.getImageResId());
    }

    @Override
    public int getItemCount() {
        return gameAssetList.size();
    }
}

