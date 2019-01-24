package ru.pushapp.backgroungonmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.pushapp.backgroungonmobile.CategoryActivity;
import ru.pushapp.backgroungonmobile.ImageModel;
import ru.pushapp.backgroungonmobile.R;
import ru.pushapp.backgroungonmobile.SelectedImageActivity;

public class rvAdapter extends RecyclerView.Adapter<rvAdapter.rvAdapterHolder> {

    class rvAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;
        public TextView description_text;
        public ImageView description_icon;
        public LinearLayout layout_description;
        public CardView cardView;

        rvAdapterHolder(View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.item_card);
            description_icon = itemView.findViewById(R.id.image_item_icon_description);
            description_text = itemView.findViewById(R.id.image_item_description);
            layout_description = itemView.findViewById(R.id.item_desc_layout);

            imageView = itemView.findViewById(R.id.image_item);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            Intent intent;

            if (typeContent == TYPE_CONTENT_CATEGORY){
                intent = new Intent(context, CategoryActivity.class);
                intent.putExtra("ImageURL", list_items.get(pos).getUrl());
                intent.putExtra("ImageCategory", list_items.get(pos).getCategory());
                intent.putExtra("ImageCountLikes", String.valueOf(list_items.get(pos).getCountLikes()));
                intent.putExtra("ImageCountDownload", String.valueOf(list_items.get(pos).getCountDownload()));
            }else{
                intent = new Intent(context, SelectedImageActivity.class);
                intent.putExtra("ImageURL", list_items.get(pos).getUrl());
                intent.putExtra("ImageCountLikes", String.valueOf(list_items.get(pos).getCountLikes()));
                intent.putExtra("ImageCountDownload", String.valueOf(list_items.get(pos).getCountDownload()));
            }
            context.startActivity(intent);
        }
    }


    final int TYPE_CONTENT_CATEGORY = 1;
    final int TYPE_CONTENT_LAST_IMAGE = 2;
    final int TYPE_CONTENT_BEST_IMAGE = 3;
    final int TYPE_CONTENT_SIMILAR_IMAGE = 4;

    int descriptionsFieldVisibility = View.VISIBLE;
    int descriptionsIconVisibility = View.VISIBLE;

    int typeContent;
    int layout_id = R.layout.image_item_layout;

    private Context context;
    private LayoutInflater inflater;
    private List<ImageModel> list_items;

    public rvAdapter(Context context, List<ImageModel> items, int typeContent) {
        this.list_items = items;
        this.context = context;
        this.typeContent = typeContent;

        inflater = LayoutInflater.from(context);

        switch (typeContent) {
            case TYPE_CONTENT_CATEGORY: {
                descriptionsFieldVisibility = View.VISIBLE;
                descriptionsIconVisibility = View.GONE;
                break;
            }
            case TYPE_CONTENT_LAST_IMAGE: {
                descriptionsFieldVisibility = View.GONE;
                descriptionsIconVisibility = View.GONE;
                break;
            }
            case TYPE_CONTENT_BEST_IMAGE: {
                descriptionsFieldVisibility = View.VISIBLE;
                descriptionsIconVisibility = View.VISIBLE;
                break;
            }
            case TYPE_CONTENT_SIMILAR_IMAGE: {
                descriptionsFieldVisibility = View.GONE;
                descriptionsIconVisibility = View.GONE;
                layout_id = R.layout.similar_image_layout;
                break;
            }
        }
    }

    @Override
    public rvAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(layout_id, parent, false);
        return new rvAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final rvAdapterHolder holder, int position) {

        try {
            holder.layout_description.setVisibility(descriptionsFieldVisibility);
            holder.description_icon.setVisibility(descriptionsIconVisibility);

            if (list_items.get(position).getCategory() == null)
                holder.description_text.setText(String.valueOf(list_items.get(position).getCountDownload()));
            else
                holder.description_text.setText(String.valueOf(list_items.get(position).getCategory()));
        } catch (Exception ignored) {}

        Glide.with(context)
                .load(list_items.get(position).getUrl())
                .into(holder.imageView);

        //set margin without CardView 8pd for Category screen
        if (typeContent == TYPE_CONTENT_CATEGORY || typeContent == TYPE_CONTENT_SIMILAR_IMAGE) {
            ViewGroup.MarginLayoutParams cardViewMarginParams = (ViewGroup.MarginLayoutParams) holder.cardView.getLayoutParams();
            cardViewMarginParams.setMargins(8, 8, 8, 8);

            holder.cardView.requestLayout();
        }
    }

    @Override
    public int getItemCount() {
        return list_items.size();
    }

}