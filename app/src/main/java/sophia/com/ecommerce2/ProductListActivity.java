package sophia.com.ecommerce2;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ProductAdapter;
import sophia.com.ecommerce2.model.Product;

public class ProductListActivity extends AppCompatActivity implements OnAdapterItemClickListener{


    private List<Product> productlist = new ArrayList<>();
    private RecyclerView    productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productRecyclerView = (RecyclerView)findViewById(R.id.product_recycle_view);


        GridLayoutManager layout = new GridLayoutManager(this, 1);
        productRecyclerView.setLayoutManager(layout);


        for (int i =0; i < 50; i++){
            productlist.add(new Product(1, "http://clicksolutions.it/wp-content/uploads/2016/04/privati.jpg","Pc", 13.99, "blalvvlalvlalvallv" ));
        }

        ProductAdapter productAdapter = new ProductAdapter(productlist, this);
        productRecyclerView.setAdapter(productAdapter);


    }

    @Override
    public void OnItemClick(int position) {

        Intent i = new Intent(this, ProductViewActivity.class);
        startActivity(i);

    }

    @Override
    public void OnAddToCartClick(int position) {

        Log.d("ProductListActivity","Stampato add to cart: " + position);

        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.product_recycle_view),
                "added to cart", Snackbar.LENGTH_LONG);
        mySnackbar.setAction("annulla", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProductListActivity","Annullato");
            }
        });
        mySnackbar.show();
    }

    @Override
    public void OnShareClick(int position) {

        Log.d("ProductListActivity","Stampato share" + position);
    }
}
