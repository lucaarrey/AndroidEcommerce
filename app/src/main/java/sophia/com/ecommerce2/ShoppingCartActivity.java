package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class ShoppingCartActivity extends AppCompatActivity {

    private RecyclerView shoppingCartRecycleView;
    private RecyclerView.LayoutManager shoppinCartLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);


        shoppingCartRecycleView = (RecyclerView) findViewById(R.id.shopping_cart_recycleView);

        shoppingCartRecycleView.setLayoutManager(new LinearLayoutManager(this));
        shoppingCartRecycleView.setHasFixedSize(true);


    }
}
