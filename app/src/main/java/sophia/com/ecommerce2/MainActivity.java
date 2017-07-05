package sophia.com.ecommerce2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.Database.EcommerceOpenHelper;
import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private RecyclerView categoryRecyclerView;
    private RecyclerView.LayoutManager categoryLayoutManager;
    private List<Category> categorylist = new ArrayList<>();

    private SharedPreferences preferences;

    private EcommerceOpenHelper ecommerceDB;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ecommerceDB = new EcommerceOpenHelper(this);


        //TextView benvenuto = (TextView)findViewById(R.id.benvenuto);
        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.category_recycle_view),
                "BENVENUTO!!!", Snackbar.LENGTH_LONG);

        preferences
                = getSharedPreferences("ecommerce", MODE_PRIVATE);

        boolean firstUser = preferences.getBoolean("firstUser", true);

        if(firstUser == true){
            mySnackbar.show();
        }

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstUser", false); //se putBoolean settato a true sempre visibile; false solo la prima volta
        editor.apply();



        categoryRecyclerView = (RecyclerView) findViewById(R.id.category_recycle_view);

        categoryLayoutManager = new LinearLayoutManager(this);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setHasFixedSize(true);

//        for (int i = 0; i < 20; i++){
//            categorylist.add(new Category("https://www.money.it/local/cache-vignettes/L600xH377/immagini_buon_compleanno_amore_frasi_auguri_di_buon_compleanno_2_whatsapp-eb1fb.jpg?1495219739", "title", "subtitle"));
//
//
//        }

        categorylist = ecommerceDB.getallcategory();

        CategoryAdapter categoryAdapter = new CategoryAdapter(categorylist, this);
        categoryRecyclerView.setAdapter(categoryAdapter);



    }

    @Override
    public void OnItemClick(int position) {
        Intent i = new Intent(this,ProductListActivity.class );
        i.putExtra("categoryId", categorylist.get(position).getmId());
        startActivity(i);
    }

    @Override
    public void OnAddToCartClick(int position) {
    }

    @Override
    public void OnShareClick(int position) {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Snackbar mySnackbar = Snackbar.make(findViewById(R.id.category_recycle_view),
                "Property changed " + key,
                Snackbar.LENGTH_LONG);
        mySnackbar.show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        preferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        preferences.unregisterOnSharedPreferenceChangeListener(this);

    }

    public void buttonOnClick(View view) {
        SharedPreferences preferences
                = getSharedPreferences("ecommerce", MODE_PRIVATE);
        boolean b = preferences.getBoolean("firstUser", true);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("firstUser", !b);
        editor.apply();

    }
}
