package sg.edu.rp.c346.id20018621.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import sg.edu.rp.c346.id20018621.ndpsongs.DBHelper;
import sg.edu.rp.c346.id20018621.ndpsongs.R;
import sg.edu.rp.c346.id20018621.ndpsongs.SongList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etSingers, etYear;
    RadioGroup radioGrp;
    Button btnInsert, btnShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle  = findViewById(R.id.etTitle);
        etSingers= findViewById(R.id.etSingers);
        etYear= findViewById(R.id.etYear);
        radioGrp = findViewById(R.id.radioGrp);
        btnInsert = findViewById(R.id.btnInsert);
        btnShow= findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,
                        SongList.class);

                startActivity(i);

            }
        });


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSingers.getText().toString();
                int year = 0;

                if(!etYear.getText().toString().isEmpty()){
                    year = Integer.parseInt(etYear.getText().toString());
                }

                int stars = 1 ;

                if(radioGrp.getCheckedRadioButtonId() == R.id.rb1){
                    stars=1;
                }
                else if(radioGrp.getCheckedRadioButtonId() == R.id.rb2){
                    stars=2;
                }
                else if(radioGrp.getCheckedRadioButtonId() == R.id.rb3){
                    stars=3;
                }
                else if(radioGrp.getCheckedRadioButtonId() == R.id.rb4){
                    stars=4;
                }
                else if(radioGrp.getCheckedRadioButtonId() == R.id.rb5){
                    stars=5;
                }

                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertSong( title,  singer,  year,  stars  );

            }
        });



    }
}