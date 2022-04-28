package android.projet.ult_dash;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText matricule, name , contact , dob , specialite ;
    Button insert , update , delete , view ;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        matricule = findViewById(R.id.matricule);
        name = findViewById(R.id.name);
        contact = findViewById(R.id.contact);
        dob = findViewById(R.id.dob);
        specialite = findViewById(R.id.specialite);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matriculeTXT = matricule.getText().toString();
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                String specialiteTXT = specialite.getText().toString();

                Boolean checkinsert = DB.insertstudent(matriculeTXT,nameTXT,contactTXT,dobTXT,specialiteTXT);
                if (checkinsert==true){
                    Toast.makeText(MainActivity.this, "New Student Inserted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "New Student Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matriculeTXT = matricule.getText().toString();
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();
                String specialiteTXT = specialite.getText().toString();

                Boolean checkupdate = DB.updatestudent(matriculeTXT,nameTXT,contactTXT,dobTXT,specialiteTXT);
                if (checkupdate==true){
                    Toast.makeText(MainActivity.this, "Student Updated", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Student Not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String matriculeTXT = matricule.getText().toString();
                Boolean checkdelete = DB.deletestudent(matriculeTXT);
                if (checkdelete==true){
                    Toast.makeText(MainActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Student Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getstudents();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Student Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Matricule:"+res.getString(0)+"\n");
                    buffer.append("Name:"+res.getString(1)+"\n");
                    buffer.append("Phone:"+res.getString(2)+"\n");
                    buffer.append("date Of Birth:"+res.getString(3)+"\n");
                    buffer.append("specialite:"+res.getString(4)+"\n\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });


    }
}