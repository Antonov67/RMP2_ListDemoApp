package com.example.rmp2_listdemoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText textField;
    ListView listView;
    Button button;
    CustomAdapter adapter;
    List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.textField);
        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);

        initData();

        adapter = new CustomAdapter(
                this,
                dataList
        );

        listView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToList();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                dataList.remove(i);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Удалено!", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

    }

    public void addItemToList(){
        String text = textField.getText().toString().trim();
        if (!text.isEmpty()){
            dataList.add(text);
            adapter.notifyDataSetChanged();
            textField.setText("");
            listView.smoothScrollToPosition(dataList.size() - 1);
        } else {
            Toast.makeText(this, "Введите текст!", Toast.LENGTH_SHORT).show();
        }
    }

    public void initData(){
        dataList = new ArrayList<>();
        dataList.add("Java");
        dataList.add("Python");
        dataList.add("C#");
    }
}