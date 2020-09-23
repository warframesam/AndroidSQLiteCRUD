package com.example.androidsqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SQLiteDBConnect sqLiteDBConnect;
    private EditText nameInsert, idUpdate, nameUpdate, idDelete;
    private TextView outputData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sqLiteDBConnect = new SQLiteDBConnect(this);
        nameInsert = findViewById(R.id.nameInsert);
        idUpdate = findViewById(R.id.idUpdate);
        nameUpdate = findViewById(R.id.nameUpdate);
        idDelete = findViewById(R.id.idDelete);

        outputData = findViewById(R.id.outputData);
    }

    public void createTable(View view) {
        try {
            sqLiteDBConnect.getReadableDatabase();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void insertValue(View view) {
        try {
            SQLiteDatabase sqLiteDatabase = sqLiteDBConnect.getWritableDatabase();

            if (sqLiteDatabase != null) {
                if (!nameInsert.getText().toString().isEmpty()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("NAME", nameInsert.getText().toString());
                    long insertSuccess = sqLiteDatabase.insert("STUDENT", null, contentValues);

                    if (insertSuccess != -1)
                        Toast.makeText(this, "Value Inserted Successfully!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Error While Inserting Value!", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "NAME Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Database NULL!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void readTable(View view) {
        try {
            SQLiteDatabase sqLiteDatabase = sqLiteDBConnect.getReadableDatabase();

            if(sqLiteDatabase!=null) {
                Cursor cursor =  sqLiteDatabase.rawQuery("SELECT * FROM STUDENT", null);
                StringBuffer stringBuffer = new StringBuffer().append("ID\t|\tNAME\n");

                if(cursor.getCount()!=0) {
                    while(cursor.moveToNext())
                        stringBuffer.append("" + cursor.getInt(0) + "\t|\t" + cursor.getString(1) + "\n");
                    outputData.setText(stringBuffer);
                }
                else
                    Toast.makeText(this, "Table Empty!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Database NULL!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateValue(View view) {
        try {
            SQLiteDatabase sqLiteDatabase = sqLiteDBConnect.getWritableDatabase();

            if(sqLiteDatabase!=null) {
                if(!idUpdate.getText().toString().isEmpty() && !nameUpdate.getText().toString().isEmpty()) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("NAME", nameUpdate.getText().toString());
                    long updateSuccess = sqLiteDatabase.update("STUDENT", contentValues, "ID=" + idUpdate.getText().toString(), null);

                    if(updateSuccess!=-1)
                        Toast.makeText(this, "Value Updated Successfully!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Error While Updating Value!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "ID and NEW NAME Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Database NULL!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteValue(View view) {
        try {
            SQLiteDatabase sqLiteDatabase = sqLiteDBConnect.getWritableDatabase();

            if(sqLiteDatabase!=null) {
                if(!idDelete.getText().toString().isEmpty()) {

                    long deleteSuccess = sqLiteDatabase.delete("STUDENT", "ID=" + idDelete.getText().toString(), null);

                    if(deleteSuccess!=-1)
                        Toast.makeText(this, "Value Deleted Successfully!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Error While Deleting Value!", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(this, "ID Cannot Be Empty!", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Database NULL!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e) {
            Toast.makeText(this, "Error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}