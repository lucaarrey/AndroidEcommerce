package sophia.com.ecommerce2.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sophia.com.ecommerce2.Category;
import sophia.com.ecommerce2.model.Product;

/**
 * Created by archimede on 26/06/17.
 */

public class EcommerceOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = EcommerceOpenHelper.class.getSimpleName();


    private static final int DATABASE_VERSION = 2;
    private static final String ECOMMERCE_CATEGORY_TABLE = "category";
    private static final String DATABASE_ECOMMERCE = "Ecommerce";

    public static final String KEY_ID = "_id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_SUBTITLE = "subtitle";
    public static final String KEY_IMAGEPATH = "imagePath";

    private static final String[] COLUMNS = { KEY_ID, KEY_TITLE, KEY_SUBTITLE, KEY_IMAGEPATH};

//    //private int id;
//    private String imagePath;
//    private String name;
//    private double price;
//    private String description;
//    private String[] photo;
//    private int stars;
//    private String longDescription;


    private static final String PRODUCT_TABLE = "product";
    public static final String KEY_ID_PRODUCT = "_id";
    public static final String KEY_PRODUCT_NAME= "name";
    public static final String KEY_PRODUCT_DESCRIPTION = "description";
    public static final String KEY_PRODUCT_LONG_DESCRIPTION = "long_description";
    public static final String KEY_PRODUCT_STARS = "stars";
    public static final String KEY_PRODUCT_IMG_PRODUCT = "imagePath";
    public static final String KEY_PRODUCT_PHOTO = "img_product";
    public static final String KEY_PRODUCT_PRICE = "price";

    public static final String FOREIGN_KEY_CATEGORY = "category";


    private static final String[] COLUMNS_PRODUCT = { KEY_ID_PRODUCT,
            KEY_PRODUCT_NAME,
            KEY_PRODUCT_DESCRIPTION,
            KEY_PRODUCT_LONG_DESCRIPTION,
            KEY_PRODUCT_STARS,
            KEY_PRODUCT_IMG_PRODUCT,
            KEY_PRODUCT_PRICE,
            FOREIGN_KEY_CATEGORY};


    //private static final String WORD_LIST_TABLE_CREATE =
    //        "CREATE TABLE " + WORD_LIST_TABLE + " (" +
    //                KEY_ID + " INTEGER PRIMARY KEY, " +
    //                // id will auto-increment if no value passed
    //                KEY_WORD + " TEXT );";

    //CREATE TABLE "category" ("_id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "title" VARCHAR NOT NULL , "subtitle" TEXT, "imagePath" VARCHAR)

    private static final String ECOMMERCE_TABLE_CATEGORY_CREATE =
            "CREATE TABLE " + ECOMMERCE_CATEGORY_TABLE + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    KEY_TITLE + " VARCHAR NOT NULL, " +
                    KEY_SUBTITLE + " TEXT, " +
                    KEY_IMAGEPATH + " VARCHAR);";

    private static final String ECOMMERCE_TABLE_PRODUCT_CREATE =
            "CREATE TABLE " + PRODUCT_TABLE + " (" +
                    KEY_ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    KEY_PRODUCT_NAME + " VARCHAR NOT NULL, " +
                    KEY_PRODUCT_DESCRIPTION + " TEXT, " +
                    KEY_PRODUCT_LONG_DESCRIPTION + " TEXT, " +
                    KEY_PRODUCT_STARS + " FLOAT, " +
                    KEY_PRODUCT_IMG_PRODUCT + " VARCHAR, " +
                    KEY_PRODUCT_PRICE + " DOUBLE, " +
                    FOREIGN_KEY_CATEGORY + " INTEGER);";


    private SQLiteDatabase mWritableDB;
    private SQLiteDatabase mReadableDB;




    public EcommerceOpenHelper(Context context) {
        super(context, DATABASE_ECOMMERCE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(ECOMMERCE_TABLE_CATEGORY_CREATE);

        fillDatabaseWithData(db);

        db.execSQL(ECOMMERCE_TABLE_PRODUCT_CREATE);

        fillProductWithData(db);


    }

    private void fillDatabaseWithData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();

        for (int i=0; i < 20; i++) {
            values.put(KEY_TITLE, "title");
            values.put(KEY_SUBTITLE, "subtitle" );
            values.put(KEY_IMAGEPATH, "http://www.fruitbookmagazine.it/wp-content/uploads/2017/05/shopping-cart-logo-lg-891x1024.png" );
            db.insert(ECOMMERCE_CATEGORY_TABLE, null, values);
        }
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(ECOMMERCE_TABLE_PRODUCT_CREATE);

        fillProductWithData(db);

    }

    private void fillProductWithData(SQLiteDatabase db) {

        ContentValues values = new ContentValues();

        for (int i=0; i < 20; i++) {
            values.put(KEY_PRODUCT_NAME, "name");
            values.put(KEY_PRODUCT_DESCRIPTION, "description" );
            values.put(KEY_PRODUCT_LONG_DESCRIPTION, "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent a vehicula tortor, non imperdiet lacus. Sed id tortor molestie, efficitur justo eget, convallis sapien. Vivamus at elit elit. Mauris pharetra purus ut neque egestas, eu ultricies erat venenatis. Duis accumsan vulputate efficitur. Etiam at dictum justo. Mauris aliquet tortor quis. " );
            values.put(KEY_PRODUCT_STARS, 4.5 );
            values.put(KEY_PRODUCT_IMG_PRODUCT, "http://mtpcon.com/wp-content/uploads/sites/1/2014/10/mtp.png" );
            values.put(KEY_PRODUCT_PRICE, 42.43);
            values.put(FOREIGN_KEY_CATEGORY, 1 );
            db.insert(PRODUCT_TABLE, null, values);
        }

    }

    public Category getcategory(int position) {

        String query = "SELECT  * FROM " + ECOMMERCE_CATEGORY_TABLE +
                " ORDER BY " + KEY_TITLE + " ASC " +
                "LIMIT " + position + ",1";

        Cursor cursor = null;

        Category entry = new Category();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);
            cursor.moveToFirst();
            entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
            entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
            entry.setSubTitle(cursor.getString(cursor.getColumnIndex(KEY_SUBTITLE)));
            entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_IMAGEPATH)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return entry;
        }

    }

    public Product getProduct(int productId) {
        String query = "SELECT * FROM " + PRODUCT_TABLE + " WHERE " + KEY_ID_PRODUCT + " = ?";
        Cursor cursor = null;

        Product entry = new Product();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, new  String[]{String.valueOf(productId)});
            cursor.moveToFirst();
            entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_PRODUCT)));
            entry.setName(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_NAME)));
            entry.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_DESCRIPTION)));
            entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_IMG_PRODUCT)));
            entry.setLongDescription(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_LONG_DESCRIPTION)));
            entry.setPrice(cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCT_PRICE)));
            entry.setStars(cursor.getFloat(cursor.getColumnIndex(KEY_PRODUCT_STARS)));
            entry.setForeignkey(cursor.getInt(cursor.getColumnIndex(FOREIGN_KEY_CATEGORY)));
        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return entry;
        }

    }


    public List<Category> getallcategory() {
        String query = "SELECT  * FROM " + ECOMMERCE_CATEGORY_TABLE;


        Cursor cursor = null;

        List<Category> c = new ArrayList<>();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, null);

            while (cursor.moveToNext()){
                Category entry = new Category();
                entry.setmId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                entry.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                entry.setSubTitle(cursor.getString(cursor.getColumnIndex(KEY_SUBTITLE)));
                entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_IMAGEPATH)));

                c.add(entry);
            }


        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return c;
        }
    }

    public List<Product> getallproduct(int categoryId) {
        String query = "SELECT  * FROM " + PRODUCT_TABLE + " WHERE " + FOREIGN_KEY_CATEGORY + " = ?";


        Cursor cursor = null;

        List<Product> c = new ArrayList<>();

        try {
            if (mReadableDB == null) {
                mReadableDB = getReadableDatabase();
            }
            cursor = mReadableDB.rawQuery(query, new String[]{String.valueOf(categoryId)});

            while (cursor.moveToNext()){
                Product entry = new Product();
                entry.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID_PRODUCT)));
                entry.setName(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_NAME)));
                entry.setDescription(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_DESCRIPTION)));
                entry.setLongDescription(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_LONG_DESCRIPTION)));
                entry.setImagePath(cursor.getString(cursor.getColumnIndex(KEY_PRODUCT_IMG_PRODUCT)));
                entry.setPrice(cursor.getDouble(cursor.getColumnIndex(KEY_PRODUCT_PRICE)));
                entry.setStars(cursor.getFloat(cursor.getColumnIndex(KEY_PRODUCT_STARS)));
                entry.setForeignkey(cursor.getInt(cursor.getColumnIndex(FOREIGN_KEY_CATEGORY)));

                c.add(entry);
            }


        } catch (Exception e) {
            Log.d(TAG, "QUERY EXCEPTION! " + e.getMessage());
        } finally {
            cursor.close();
            return c;
        }
    }


}
