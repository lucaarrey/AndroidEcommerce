package sophia.com.ecommerce2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import sophia.com.ecommerce2.R;
import sophia.com.ecommerce2.model.Product;

/**
 * Created by archimede on 14/06/17.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;
    private OnAdapterItemClickListener listener;

    private NumberFormat format;// = NumberFormat.getCurrencyInstance();

    public ProductAdapter(List<Product> productList, Context context) {
        this.productList = productList;
        this.context = context;
        this.listener = (OnAdapterItemClickListener)context;

        Locale l = new Locale("it");
        format = NumberFormat.getCurrencyInstance(l);


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_row_adapter, parent, false);

        ProductAdapter.ViewHolder vh = new ProductAdapter.ViewHolder(v);

        vh.img_product = (ImageView) v.findViewById(R.id.img_product);
        vh.name = (TextView) v.findViewById(R.id.name);
        vh.description = (TextView)v.findViewById(R.id.description);
        vh.price = (TextView)v.findViewById((R.id.price));
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
        holder.name.setText(productList.get(position).getName());
        holder.description.setText(productList.get(position).getDescription());
        Picasso.with(context).load(productList.get(position).getImagePath()).into(holder.img_product);
        holder.price.setText(format.format(productList.get(position).getPrice()));

        holder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img_product;
        public TextView name;//(a) procedimento con passaggio al costruttore, passo 1
        public TextView description;
        public TextView price;

        public ViewHolder(final View container) {
            super(container);

        }
    }
}
