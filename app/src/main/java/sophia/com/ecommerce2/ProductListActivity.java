package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ProductAdapter;
import sophia.com.ecommerce2.model.Product;

public class ProductListActivity extends AppCompatActivity implements OnAdapterItemClickListener{


    private List<Product> productlist = new ArrayList<>();
    private RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productRecyclerView = (RecyclerView)findViewById(R.id.product_recycle_view);


        GridLayoutManager layout = new GridLayoutManager(this, 1);
        productRecyclerView.setLayoutManager(layout);


        for (int i =0; i < 50; i++){
            productlist.add(new Product(1, "http://clicksolutions.it/wp-content/uploads/2016/04/privati.jpg","Pc", 13.2, "blalvvlalvlalvallv" ));
        }

        ProductAdapter productAdapter = new ProductAdapter(productlist, this);
        productRecyclerView.setAdapter(productAdapter);


    }

    @Override
    public void OnItemClick(int position) {

    }
}
