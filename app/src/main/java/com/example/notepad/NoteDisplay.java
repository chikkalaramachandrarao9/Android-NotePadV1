package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteDisplay extends AppCompatActivity {

    EditText title,note;
    String s1,s2;
    Intent in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_display);
        Intent i = getIntent();
        s1 = i.getStringExtra("title");
        s2 = i.getStringExtra("note");
        title = (EditText) findViewById(R.id.titleDisplay);
        note = (EditText) findViewById(R.id.note);
        if(!s1.equals(" ") && !s2.equals(" ")) {
            title.setText(s1);
            note.setText(s2);
        }
        in = new Intent(NoteDisplay.this,MainActivity.class);
    }

    public void back(View view) {
        startActivity(in);
    }

    public void delete(View view) {
        String query = null;
//        final int[] i = new int[1];
        SQLiteDatabase database = this.openOrCreateDatabase("Notes",MODE_PRIVATE,null);
//        new  AlertDialog.Builder(this)
//                .setIcon(android.R.drawable.ic_btn_speak_now)
//                .setTitle("CONFIRM?")
//                .setMessage("Do you want to delete this note?")
//                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                i[0] =1;
//            }
//        }).show();
//        if(i[0] !=1) {
//
//        }
        query = "Delete from notes where title = '"+ s1+"' and note = '"+s2+"'";
        Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT).show();
        database.execSQL(query);
        startActivity(in);
    }

    public void save(View view) {
        SQLiteDatabase database = this.openOrCreateDatabase("Notes",MODE_PRIVATE,null);
        String query = null;
        if(s1.equals(" ") && s2.equals(" ")) {

            query = "INSERT INTO notes values('"+title.getText().toString()+"','"+note.getText().toString()+"')";
//            Log.i("hello",query);
        }
        else{
            query = "UPDATE notes set title = '" + title.getText().toString() + "', note = '" + note.getText().toString() + "' where title = '" + s1 + "' and note = '" + s2 + "'";
        }
        Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_SHORT).show();
        database.execSQL(query);
        startActivity(in);
    }
}
