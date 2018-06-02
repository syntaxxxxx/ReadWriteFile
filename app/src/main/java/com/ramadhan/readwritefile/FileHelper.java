package com.ramadhan.readwritefile;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileHelper {

    static void writeToFile(String fileName, String data, Context c) {

        // openFileOutput membuka berkas sesuai dengan nama nya
        try {
            OutputStreamWriter osw = new OutputStreamWriter(
                    c.openFileOutput(fileName, Context.MODE_PRIVATE));
            osw.write(data);
            osw.close();

        } catch (IOException e) {
            Log.e("Exception", "File Write Failed" + e.toString());
        }
    }


    static String readFromFile(Context c, String fileName) {

        String ret = "";

        try {
            InputStream stream = c.openFileInput(fileName);

            if (stream != null) {

                // data pada berkas akan dibaca
                InputStreamReader inputStreamReader = new InputStreamReader(stream);

                // setiap data tiap baris dalam berkas akan diperoleh
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                // baca data tiap baris atau di loops
                while ((receiveString = bufferedReader.readLine()) != null) {

                    // jika ada data maka akan ditambahkan kedalam objek stringBuilder
                    stringBuilder.append(receiveString);
                }

                inputStreamReader.close();
                ret = stringBuilder.toString();

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }
        return ret;

    }
}
