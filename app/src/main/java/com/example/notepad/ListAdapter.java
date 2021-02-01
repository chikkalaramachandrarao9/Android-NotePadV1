package com.example.notepad;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ListAdapter extends ArrayAdapter<Notes> {

    int resource;
    List<Notes> list;
    Context context;


    public ListAdapter(@NonNull Context context,int resource, @NonNull List objects) {
        super(context,resource,objects);
        this.context = context;
        this.list = objects;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);


        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        TextView textView = view.findViewById(R.id.noteTitle);

        //getting the hero of the specified position
        Notes n = list.get(position);

        //adding values to the list item
        textView.setText(n.getTitle());

        return view;
    }
}
