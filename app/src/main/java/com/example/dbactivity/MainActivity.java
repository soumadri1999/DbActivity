package com.example.dbactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbactivity.database.NotesAdapter;
import com.example.dbactivity.database.NotesDao;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSubtitle;
    NotesDao notesDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = findViewById(R.id.etTitle);
        etSubtitle = findViewById(R.id.etSubtitle);
        notesDao = new NotesDao(this);
        notesDao.openDb();
        NotesAdapter adapter = new NotesAdapter(notesDao);
        RecyclerView mreRecyclerView = findViewById(R.id.recyclerView);
        mreRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mreRecyclerView.setAdapter(adapter);

    }

    public void dbHandler(View view) {
        switch (view.getId()){
            case R.id.btnCommit:
                saveData();
                break;
            case R.id.btnGet:
                retrieveData();
                break;
        }
    }

    private void retrieveData() {
        String row = notesDao.readRow();
        TextView tvOutput = findViewById(R.id.textView);
        tvOutput.setText(row);
    }

    private void saveData() {
        String title = etTitle.getText().toString();
        String subtitle = etSubtitle.getText().toString();
        notesDao.createRow(title,subtitle);
        etTitle.setText("");
        etSubtitle.setText("");
    }
}