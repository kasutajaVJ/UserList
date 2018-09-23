package com.example.vanessajalakas.userlist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private EditText first_name_text, last_name_text;
    private ArrayAdapter<Name> adapter;
    private List<Name> list = new ArrayList<>(Collections.singleton(new Name("Kalle", "Malle")));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        first_name_text = findViewById(R.id.first_name);
        last_name_text = findViewById(R.id.last_name);
        Button add = findViewById(R.id.btn_add);
        ListView listView = findViewById(R.id.listView1);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String first = first_name_text.getText().toString().trim();
                String last = last_name_text.getText().toString().trim();
                list.add(new Name(first, last));

                onClear(v);

                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                adb.setTitle(R.string.dialogTitle);
                final int index = position;
                adb.setPositiveButton(R.string.dialogPos, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        list.remove(index);
                        adapter.notifyDataSetChanged();
                    }
                });

                adb.setNegativeButton(R.string.dialogNeg, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                adb.show();
            }
        });
    }

    public void onClear(View view) {
        last_name_text.setText("");
        first_name_text.setText("");
        adapter.notifyDataSetChanged();
    }
}
