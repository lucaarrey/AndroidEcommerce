package sophia.com.ecommerce2.network;

import android.content.ClipData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sophia.com.ecommerce2.model.Product;
import sophia.com.ecommerce2.model.User;
import sophia.com.ecommerce2.model.UserRequest;

/**
 * Created by archimede on 05/07/17.
 */

public interface EcommerceService {

    //"https://ecommerce.getsandbox.com"
    @GET("productitem")
    Call<List<Product>> listProduct();

    @POST("login")
    Call<User> login(@Body UserRequest user);


}
