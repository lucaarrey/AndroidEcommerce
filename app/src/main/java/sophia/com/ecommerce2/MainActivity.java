package sophia.com.ecommerce2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.adapter.CategoryAdapter;
import sophia.com.ecommerce2.adapter.OnAdapterItemClickListener;

public class MainActivity extends AppCompatActivity implements OnAdapterItemClickListener {

    private RecyclerView categoryRecyclerView;
    private RecyclerView.LayoutManager categoryLayoutManager;
    private List<Category> categorylist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        categoryRecyclerView = (RecyclerView) findViewById(R.id.category_recycle_view);

        categoryLayoutManager = new LinearLayoutManager(this);
        categoryRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryRecyclerView.setHasFixedSize(true);

        for (int i = 0; i < 20; i++){
            categorylist.add(new Category("https://www.money.it/local/cache-vignettes/L600xH377/immagini_buon_compleanno_amore_frasi_auguri_di_buon_compleanno_2_whatsapp-eb1fb.jpg?1495219739", "title", "subtitle"));


        }

        CategoryAdapter categoryAdapter = new CategoryAdapter(categorylist, this);
        categoryRecyclerView.setAdapter(categoryAdapter);

    }

    @Override
    public void OnItemClick(int position) {

    }
}
