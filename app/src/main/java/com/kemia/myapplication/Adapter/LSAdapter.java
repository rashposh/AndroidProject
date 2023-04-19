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
import com.kemia.myapplication.Fetch.GoogleNewsItem;
import com.kemia.myapplication.R;
import com.kemia.myapplication.webview;

import java.util.List;
import java.util.Objects;

public class LSAdapter  extends RecyclerView.Adapter<LSAdapter.ViewHolder> {

    @NonNull
    @Override
    public LSAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View view = inflater.inflate(R.layout.testhistory, parent, false);



        // Return a new holder instance
        LSAdapter.ViewHolder viewHolder = new LSAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LSAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        GoogleNewsItem ggNewItem = ggNewItems.get(position);
        final int pos = position;
        // Set item views based on your views and data model
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

            Database db = new Database();
            db.deleteItem(view.getContext(), ggNewItem);
        });
    }

    private void itemListener(View v, int pos) {

        GoogleNewsItem googleNewsItem = ggNewItems.get(pos);
        var intent = new Intent(v.getContext(), webview.class);
        intent.putExtra("url", googleNewsItem.getLink());
        v.getContext().startActivity(intent);

        Database db = new Database();
        db.addNewsItem(googleNewsItem, v.getContext());
    }

    @Override
    public int getItemCount() {
        return ggNewItems.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public ImageView imageView;

        public Button deleteBtn;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
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

    // Pass in the contact array into the constructor
    public LSAdapter(List<GoogleNewsItem> ggNewItems) {
        this.ggNewItems = ggNewItems;
    }
}
