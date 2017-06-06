package sophia.com.ecommerce2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sophia.com.ecommerce2.Category;
import sophia.com.ecommerce2.R;

/**
 * Created by archimede on 06/06/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Category> categoryList;
    private Context context;
    private OnAdapterItemClickListener listener;

    public CategoryAdapter(List<Category> categoryList, Context context) {
        this.categoryList = categoryList;
        this.context = context;
        this.listener = (OnAdapterItemClickListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_row_adapter, parent, false);

        ViewHolder vh = new ViewHolder(v);

        vh.img_product = (ImageView) v.findViewById(R.id.img_product);
        vh.title = (TextView) v.findViewById(R.id.title);
        vh.subtitle = (TextView)v.findViewById(R.id.subtitle);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("OnClickListner", v.getTag().toString());

                if(listener != null) {
                    listener.OnItemClick((int) v.getTag()) ;
                }

            }
        });

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(categoryList.get(position).getTitle());
        holder.subtitle.setText(categoryList.get(position).getSubTitle());
        Picasso.with(context).load(categoryList.get(position).getImagePath()).into(holder.img_product);

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_product;
        public TextView title;//(a) procedimento con passaggio al costruttore, passo 1
        public TextView subtitle;

        public ViewHolder(final View container) {
            super(container);

        }
    }

}
