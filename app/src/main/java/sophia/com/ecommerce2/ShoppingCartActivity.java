package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import sophia.com.ecommerce2.Cart.ShoppingCart;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;
import sophia.com.ecommerce2.adapter.ShoppingCartAdapter;

public class ShoppingCartActivity extends AppCompatActivity implements OnAdapterItemClickListener{

    private RecyclerView shoppingCartRecycleView;
    private RecyclerView.LayoutManager shoppingCartLayoutManager;

    private TextView total;
    private NumberFormat format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        format = NumberFormat.getCurrencyInstance(Locale.ITALY);


        shoppingCartRecycleView = (RecyclerView) findViewById(R.id.shopping_cart_recycleView);

        shoppingCartRecycleView.setLayoutManager(new LinearLayoutManager(this));
        shoppingCartRecycleView.setHasFixedSize(true);

        ShoppingCartAdapter shoppingCartAdapter = new ShoppingCartAdapter(this);

        shoppingCartRecycleView.setAdapter(shoppingCartAdapter);

        total = (TextView) findViewById(R.id.total);

        total.setText(format.format(ShoppingCart.getInstance().totalAmount()));
    }


    @Override
    public void OnItemClick(int position) {

    }

    @Override
    public void OnAddToCartClick(int position) {

    }

    @Override
    public void OnShareClick(int position) {

    }

    @Override
    public void OnRemoveToCartClick() {

        ShoppingCart.getInstance().totalAmount();

        total.setText(format.format(ShoppingCart.getInstance().totalAmount()));


    }




}
