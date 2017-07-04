package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sophia.com.ecommerce2.Database.EcommerceOpenHelper;
import sophia.com.ecommerce2.model.Product;

public class ProductViewActivity extends AppCompatActivity {

    public ImageView img_product;
    public TextView name;
    public RatingBar stars;
    public TextView description;
    public TextView long_description;

    private EcommerceOpenHelper ecommerceDB;

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

        Product p = ecommerceDB.getProduct(productId);

        Picasso.with(this).load(p.getImagePath()).into(img_product);
        name.setText(p.getName());
        stars.setTag(p.getStars());
        description.setTag(p.getDescription());
        long_description.setText(p.getLongDescription());



    }


}
