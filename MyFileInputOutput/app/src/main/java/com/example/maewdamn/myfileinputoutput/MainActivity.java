package com.example.maewdamn.myfileinputoutput;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends Activity {

    private EditText editText;
    static final int READ_BLOCK_SIZE = 100;
    public static final String TAG = MainActivity.class.getSimpleName();
    String filename = "myFile.txt", fileInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initInstances();
    }

    protected void initInstances() {
        editText = (EditText) findViewById(R.id.edit_data);
        editText.setSelection(0);
        fileInfo = getFileStreamPath(filename).getAbsolutePath();
    }

    public void writeFile(View v) {
        try {
            FileOutputStream fileOut = openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            String data = editText.getText().toString();
            outputWriter.write(data);
            outputWriter.close();
            fileOut.close();
            String msg = "write " + data + " to file " + fileInfo;
            Log.d(TAG, msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readFile(View v) {
        String line;
        StringBuffer output = new StringBuffer();
        try {
            FileInputStream fileIn = openFileInput(filename);
            InputStreamReader inputReader = new InputStreamReader(fileIn);
            BufferedReader bufferReader = new BufferedReader(inputReader);
            while ((line = bufferReader.readLine()) != null) {
                output.append(line);
                Log.d(TAG, "read " + line);
            }
            inputReader.close();
            bufferReader.close();
            fileIn.close();

            editText.setText(output);
            String msg = "read " + output + " from file " + fileInfo;
            Log.d(TAG, msg);
            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
