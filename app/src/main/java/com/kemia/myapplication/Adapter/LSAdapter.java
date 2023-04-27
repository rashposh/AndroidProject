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


        LSAdapter.ViewHolder viewHolder = new LSAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LSAdapter.ViewHolder holder, int position) {

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
            ggNewItems.remove(pos);//xóa dựa vào vị trí mảng
            notifyItemRemoved(pos);//báo cho recyculer view có 1 thg bị xóa để nó hiển thị lại
            notifyItemRangeChanged(pos, ggNewItems.size());//thông báo bị xóa chỗ nào

            Database db = new Database();
            db.deleteItem(view.getContext(), ggNewItem);//xóa nó trong database nốt
        });
    }
    private void itemListener(View v, int pos) {

        GoogleNewsItem googleNewsItem = ggNewItems.get(pos);//lấy bài báo đã nhấn
        var intent = new Intent(v.getContext(), webview.class);//tạo intent cho cái wedview
        intent.putExtra("url", googleNewsItem.getLink());// thêm url vào webview đó
        v.getContext().startActivity(intent);//mở cái wedvew

        Database db = new Database();
        db.addNewsItem(googleNewsItem, v.getContext());//thêm bài báo vào database lịch sử
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

        }
    }


    private List<GoogleNewsItem> ggNewItems;


    public LSAdapter(List<GoogleNewsItem> ggNewItems) {
        this.ggNewItems = ggNewItems;
    }
}
