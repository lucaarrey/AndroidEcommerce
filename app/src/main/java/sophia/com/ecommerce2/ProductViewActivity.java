package sophia.com.ecommerce2;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sophia.com.ecommerce2.Cart.ShoppingCart;
import sophia.com.ecommerce2.Database.EcommerceOpenHelper;
import sophia.com.ecommerce2.model.Product;
import sophia.com.ecommerce2.network.EcommerceService;

import static sophia.com.ecommerce2.R.id.price;
import static sophia.com.ecommerce2.R.id.product_recycle_view;

public class ProductViewActivity extends AppCompatActivity {

    public ImageView img_product;
    public TextView name;
    public RatingBar stars;
    public TextView description;
    public TextView long_description;
    public TextView price;

    private Product product;

    private NumberFormat format;

    private EcommerceOpenHelper ecommerceDB;

    private ProductTask mTask = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

        ecommerceDB = new EcommerceOpenHelper(this);

        img_product = (ImageView)findViewById(R.id.img_product);
        name = (TextView) findViewById(R.id.name);
        stars = (RatingBar)findViewById(R.id.stars);
        description = (TextView) findViewById(R.id.description);
        long_description = (TextView) findViewById(R.id.long_description);
        price = (TextView) findViewById((R.id.price));

        format = NumberFormat.getCurrencyInstance(Locale.ITALY);


//        Product p = new Product(1,
//                "http://clicksolutions.it/wp-content/uploads/2016/04/privati.jpg",
//                "PC",
//                43.99,
//                "adfnadkònf",
//                3,
//                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec nec eros interdum felis faucibus hendrerit ac at risus. Nulla convallis id urna id bibendum. Curabitur maximus sem imperdiet enim tristique, in euismod quam aliquam. Proin sed dui vitae dolor suscipit lobortis vel et ipsum. Praesent at est sapien. Aliquam suscipit dolor convallis turpis molestie hendrerit. In condimentum rutrum dapibus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis neque est, pharetra eget elementum eu, convallis et nibh. Integer gravida suscipit felis sit amet aliquam. Mauris tempor enim non neque aliquet volutpat. Pellentesque sed nunc vitae."
//                 1);

//
        int productId = getIntent().getIntExtra("productId", -1);

        //Product p = ecommerceDB.getProduct(productId);

//        Picasso.with(this).load(p.getImagePath()).into(img_product);
//        name.setText(p.getName());
//        stars.setTag(p.getStars());
//        description.setTag(p.getDescription());
//        long_description.setText(p.getLongDescription());

        mTask = new ProductTask();
        mTask.execute((Void) null);

    }

    public void addToCartClick(View view) {

        ShoppingCart.getInstance().addProduct(product);

        Snackbar mySnackbar = Snackbar.make(price,
            "added to cart", Snackbar.LENGTH_LONG);
        mySnackbar.setAction("Annulla", new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.d("ProductViewActivity","Annullato");
                ShoppingCart.getInstance().addProduct(product);
            }
        });
        mySnackbar.show();
    }


    public class ProductTask extends AsyncTask<Void, Void, Product>{
        @Override
        protected Product doInBackground(Void... params) {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://ecommerce2.getsandbox.com").addConverterFactory(GsonConverterFactory.create())
                    .build();

            EcommerceService service = retrofit.create(EcommerceService.class);

            int productId = getIntent().getIntExtra("productId", 0);
            Call<Product> productCall = service.product(productId);

            try {
                Response<Product> productResponse = productCall.execute();

                if (productResponse.isSuccessful()){

                    return productResponse.body();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Product p){

            if (p == null) return;

            Picasso.with(ProductViewActivity.this).load(p.getImagePath()).into(img_product);
            name.setText(p.getName());
            stars.setRating(p.getStars());
            description.setText(p.getDescription());
            long_description.setText(p.getLongDescription());
            price.setText(format.format(p.getPrice()));

            product = p;

        }
    }



}
