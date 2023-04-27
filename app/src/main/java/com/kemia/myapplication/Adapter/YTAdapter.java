package com.kemia.myapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kemia.myapplication.Data.Database;
import com.kemia.myapplication.DataYT.Databaseyt;
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;
import com.kemia.myapplication.webview;

import java.util.List;
import java.util.Objects;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.ViewHolder> {

    @NonNull
    @Override
    public YTAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.testlike, parent, false);



        // Return a new holder instance
        YTAdapter.ViewHolder viewHolder = new YTAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YTAdapter.ViewHolder holder, int position) {

        GoogleNewsItem ggNewItem = ggNewItems.get(position);
        final int pos = position;
        TextView textView = holder.nameTextView;
        textView.setText(ggNewItem.getTitle());
        ImageView imageView = holder.imageView;

        if (!Objects.isNull(ggNewItem.getImgBitMap()))
            imageView.setImageBitmap(ggNewItem.getImgBitMap());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener(v, pos);
            }
        });

        holder.nameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemListener(v, pos);
            }
        });

        holder.deleteBtn.setOnClickListener(view -> {
            ggNewItems.remove(pos);
            notifyItemRemoved(pos);
            notifyItemRangeChanged(pos, ggNewItems.size());

            Databaseyt db = new Databaseyt();
            db.deleteItem(view.getContext(), ggNewItem);
        });
    }

    private void itemListener(View v, int pos) {

        GoogleNewsItem googleNewsItem = ggNewItems.get(pos);
        var intent = new Intent(v.getContext(), webview.class);
        intent.putExtra("url", googleNewsItem.getLink());
        v.getContext().startActivity(intent);

        Databaseyt db = new Databaseyt();
        db.addNewsItem(googleNewsItem, v.getContext());
    }

    @Override
    public int getItemCount() {
        return ggNewItems.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView nameTextView;
        public ImageView imageView;

        public Button deleteBtn;

        public ViewHolder(View itemView) {

            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.textView3);
            imageView = (ImageView) itemView.findViewById(R.id.imageView4);
            deleteBtn = (Button) itemView.findViewById(R.id.btnxoa);
        }

        @Override
        public void onClick(View v) {
            System.out.println(getLayoutPosition());
        }
    }


    private List<GoogleNewsItem> ggNewItems;

    public YTAdapter(List<GoogleNewsItem> ggNewItems) {
        this.ggNewItems = ggNewItems;
    }
}
