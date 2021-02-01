package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),"Press 3 dots on right hand corner to add new note",Toast.LENGTH_LONG).show();
        final ArrayList<Notes> list = new ArrayList<Notes>();
        ListView listView  = (ListView) findViewById(R.id.homeList);
        try {


            SQLiteDatabase database = this.openOrCreateDatabase("Notes", MODE_PRIVATE, null);

            database.execSQL("CREATE TABLE IF NOT EXISTS notes (title VARCHAR, note VARCHAR)");

//            database.execSQL("INSERT INTO notes(title,note) values('hi','hello')");
//
//            database.execSQL("INSERT INTO notes(title,note) values('ajsl;jalkjkajlk','hello')");
            Cursor c = database.rawQuery("SELECT * FROM notes",null);

            int titleIndex = c.getColumnIndex("title");
            int noteIndex = c.getColumnIndex("note");

            c.moveToFirst();



            while(c != null){


                String title = c.getString(titleIndex);
                String note = c.getString(noteIndex);
                Notes n = new Notes(title,note);
                list.add(n);
                c.moveToNext();
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

        ListAdapter adapter = new ListAdapter(this,R.layout.listview,list);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this,NoteDisplay.class);
                Notes n = list.get(position);
                i.putExtra("title",n.getTitle());
                i.putExtra("note",n.getNote());
                startActivity(i);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void newNote(MenuItem item) {
        Intent i = new Intent(MainActivity.this,NoteDisplay.class);
        i.putExtra("title"," ");
        i.putExtra("note"," ");
        startActivity(i);

    }
}
