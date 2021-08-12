package sg.edu.rp.c346.id20031826.sa_sugarspice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Recipe> recipeList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(this);
        recipeList = dbh.getAllRecipes();
        dbh.close();

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, recipeList);
        lv.setAdapter(adapter);

    }
}