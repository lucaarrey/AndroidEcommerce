package sophia.com.ecommerce2.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sophia.com.ecommerce2.model.Category;
import sophia.com.ecommerce2.model.Product;
import sophia.com.ecommerce2.model.User;
import sophia.com.ecommerce2.model.UserRequest;

/**
 * Created by archimede on 05/07/17.
 */

public interface EcommerceService {

    //"https://ecommerce.getsandbox.com"
    @Headers("Content-Type: application/json")
    @GET("productitem/{id}")
    Call<List<Product>> listProduct(@Path("id") int categoryId);

    @POST("login")
    Call<User> login(@Body UserRequest user);

    @Headers("Content-Type: application/json")
    @GET("category")
    Call<List<Category>> listCategory();

    @Headers("Content-Type: application/json")
    @GET("product/{id}")
    Call<Product> product(@Path("id") int productId);



}
