package sg.edu.rp.c346.id20031826.sa_sugarspice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "recipe.db";
        private static final int DATABASE_VERSION = 2;
        private static final String TABLE_RECIPE = "Recipe";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_INGREDIENTS = "ingredients";
        private static final String COLUMN_METHOD = "method";
        private static final String COLUMN_TIPS = "tips";

        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // CREATE TABLE Recipe
            // (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,
            // ingredients TEXT, method TEXT, tips TEXT);
            String createRecipeTableSql = "CREATE TABLE " + TABLE_RECIPE + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT, "
                    + COLUMN_INGREDIENTS + " TEXT, "
                    + COLUMN_METHOD + " INTEGER, "
                    + COLUMN_TIPS + " INTEGER )";
            db.execSQL(createRecipeTableSql);
            Log.i("info", createRecipeTableSql + "\ncreated tables");
        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE); //delete table and contents
        db.execSQL("ALTER TABLE " + TABLE_RECIPE + " ADD COLUMN  module_name TEXT ");
        // onCreate(db); //delete as table already created
    }

        public long insertRecipe(String name, String ingredients, String method, String tips) {
            // Get an instance of the database for writing
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME, name);
            values.put(COLUMN_INGREDIENTS, ingredients);
            values.put(COLUMN_METHOD, method);
            values.put(COLUMN_TIPS, tips);
            // Insert the row into the TABLE_RECIPE
            long result = db.insert(TABLE_RECIPE, null, values);
            // Close the database connection
            db.close();
            Log.d("SQL Insert","" + result);
            return result;
        }

        public ArrayList<Recipe> getAllRecipes() {
            ArrayList<Recipe> recipeslist = new ArrayList<Recipe>();
            String selectQuery = "SELECT " + COLUMN_ID + ","
                    + COLUMN_NAME + "," + COLUMN_INGREDIENTS + ","
                    + COLUMN_METHOD + ","
                    + COLUMN_TIPS + " FROM " + TABLE_RECIPE;

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    String ingredients = cursor.getString(2);
                    String method = cursor.getString(3);
                    String tips = cursor.getString(4);

                    Recipe newRecipe = new Recipe(id, name, ingredients, method, tips);
                    recipeslist.add(newRecipe);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return recipeslist;
        }

        public int deleteRecipe(int id) {
            SQLiteDatabase db = this.getWritableDatabase();
            String condition = COLUMN_ID + "= ?";
            String[] args = {String.valueOf(id)};
            int result = db.delete(TABLE_RECIPE, condition, args);
            db.close();
            return result;
        }
}
