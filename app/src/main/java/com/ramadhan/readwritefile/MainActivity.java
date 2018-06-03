package com.ramadhan.readwritefile;

import android.Manifest;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnNew;
    Button btnOpen;
    Button btnSave;
    EditText editContent;
    EditText editTitle;

    File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNew = findViewById(R.id.button_new);
        btnOpen = findViewById(R.id.button_open);
        btnSave = findViewById(R.id.button_save);
        editContent = findViewById(R.id.edit_file);
        editTitle = findViewById(R.id.edit_title);

        btnNew.setOnClickListener(this);
        btnOpen.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        // get berkas secara otomatis dari internal storage
        path = getFilesDir();

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.button_new:
                newFile();
                break;

            case R.id.button_open:
                openFile();
                break;

            case R.id.button_save:
                saveFile();
                break;

        }
    }


    private void saveFile() {
        if (editTitle.getText().toString().isEmpty()) {
            Toast.makeText(this, "Harus Diisi Dulu",
                    Toast.LENGTH_LONG).show();

        } else {
            String title = editTitle.getText().toString();
            String text = editContent.getText().toString();
            FileHelper.writeToFile(title, text, this);
            Toast.makeText(this, "Saving "
                    + editTitle.getText().toString()
                    + " File", Toast.LENGTH_SHORT).show();

        }
    }


    private void openFile() {
        showList();

    }


    private void showList() {
        final ArrayList<String> arrayList = new ArrayList<>();
        for (String file : path.list()) {
            arrayList.add(file);

        }

        final CharSequence items[] = arrayList.toArray(new CharSequence[arrayList.size()]);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pilih File Yang Diinginkan");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                // call method load data untuk menampilkan berkas
                loadData(items[item].toString());

            }
        });
        AlertDialog alert = builder.create();
        alert.show();

    }


    private void newFile() {
        editTitle.setText("");
        editContent.setText("");
        Toast.makeText(this, "Clearing File",
                Toast.LENGTH_SHORT).show();

    }


    // method untuk membuka dan menampilkan berkas
    private void loadData(String title) {
        String text = FileHelper.readFromFile(this, title);
        editTitle.setText(title);
        editContent.setText(text);
        Toast.makeText(this, "Loading " + title + " Data ",
                Toast.LENGTH_SHORT).show();

    }
}
