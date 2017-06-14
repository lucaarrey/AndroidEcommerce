package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView productRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        productRecycleView = (RecyclerView)findViewById(R.id.product_recycle_view);


        GridLayoutManager layout = new GridLayoutManager(this, 2);
        productRecycleView.setLayoutManager(layout);

    }
}
