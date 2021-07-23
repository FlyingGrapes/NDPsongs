package sg.edu.rp.c346.id20018621.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import sg.edu.rp.c346.id20018621.ndpsongs.DBHelper;
import sg.edu.rp.c346.id20018621.ndpsongs.R;
import sg.edu.rp.c346.id20018621.ndpsongs.Song;

public class EditSong extends AppCompatActivity {

    EditText etTitle, etSingers, etYear, etID;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etTitle  = findViewById(R.id.etTitle);
        etSingers= findViewById(R.id.etSingers);
        etYear= findViewById(R.id.etYear);
        etID= findViewById(R.id.etID);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete= findViewById(R.id.btnDelete);
        btnCancel= findViewById(R.id.btnCancel);

        etID.setText(data.getId()+"");
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(data.getYear()+"");

        if (data.getStars()==1){
            rb1.setChecked(true);
        }
        else if (data.getStars()==2){
            rb2.setChecked(true);
        }
        else if (data.getStars()==3){
            rb3.setChecked(true);
        }
        else if (data.getStars()==4){
            rb4.setChecked(true);
        }
        else if (data.getStars()==5){
            rb5.setChecked(true);
        }

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);

                int stars = 0;
                String title = etTitle.getText().toString();
                String singers = etSingers.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());

                if(rb1.isChecked()){
                    stars=1;
                }
                else if(rb2.isChecked()){
                    stars=2;
                }
                else if(rb3.isChecked()){
                    stars=3;
                }
                else if(rb4.isChecked()){
                    stars=4;
                }
                else if(rb5.isChecked()){
                    stars=5;
                }

                data.setSongContent(title, singers, year, stars);
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

    }
}