package sg.edu.rp.c346.id20031826.sa_sugarspice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    EditText etName, etIngredients, etMethod, etTips;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName = findViewById(R.id.etName);
        etIngredients = findViewById(R.id.etIngredients);
        etMethod = findViewById(R.id.etMethod);
        etTips = findViewById(R.id.etTips);
        btnSubmit = findViewById(R.id.buttonSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //check if any components are empty
                String name = etName.getText().toString().trim();
                String ingredients = etIngredients.getText().toString().trim();
                String method = etMethod.getText().toString().trim();
                if (name.length() == 0 || ingredients.length() == 0 || method.length() == 0){
                    Toast.makeText(AddActivity.this, "Incomplete data", Toast.LENGTH_SHORT).show();
                    return;
                }

                //because tips is optional
                String tips = etTips.getText().toString().trim();

                DBHelper dbh = new DBHelper(AddActivity.this);
                long result = dbh.insertRecipe(name, ingredients, method, tips);

                if (result != -1) {
                    Toast.makeText(AddActivity.this, "Recipe inserted", Toast.LENGTH_LONG).show();
                    etName.setText("");
                    etIngredients.setText("");
                    etMethod.setText("");
                    etTips.setText("");
                } else {
                    Toast.makeText(AddActivity.this, "Insert failed", Toast.LENGTH_LONG).show();
                }

                //
            }
        });
    }
}