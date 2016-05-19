package com.example.maewdamn.chakritfinal2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class ResultActivity extends AppCompatActivity {
    String filename = "stats.txt", fileInfo;
    public static final String TAG = MainActivity.class.getSimpleName();
    TextView readingResult ,winStatus, loseStatus, tieStatus;
    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        try {
            InputStream in = getResources().openRawResource(R.raw.players);
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(in, null);
            NodeList apps = doc.getElementsByTagName("player");
            for (int i = 0; i < apps.getLength(); i++) {
                items.add(((Element) apps.item(i)).getAttribute("name"));
            }

        } catch (Exception e) {
            Toast.makeText(this, "Exception " + e.toString(), Toast.LENGTH_LONG).show();
        }

        fileInfo = getFileStreamPath(filename).getAbsolutePath();
        readingResult = (TextView) findViewById(R.id.readingResult);
        winStatus = (TextView) findViewById(R.id.winStatus);
        loseStatus = (TextView) findViewById(R.id.loseStatus);
        tieStatus = (TextView) findViewById(R.id.tieStatus);

        readFile();

        winStatus.setText("Win stats: ");
        loseStatus.setText("Loss stats: ");
        tieStatus.setText("Tie stats: ");

        DB.dh = new DBHelper(this);

        for (int i=1; i <= 6; i++) {
            String getResult = displayById(i);
            String[] resultPart = getResult.split(" ");
            int scoreWinCheck = Integer.parseInt(resultPart[1]);
            int scoreLossCheck = Integer.parseInt(resultPart[2]);
            //int scoreTieCheck = Integer.parseInt(resultPart[3]);

            if (scoreWinCheck > 0) {
                winStatus.append(items.get(i-1) + " " + scoreWinCheck + " ");
            }

            if (scoreLossCheck > 0) {
                loseStatus.append(items.get(i-1) + " " + scoreLossCheck + " ");
            }

            /*if (scoreTieCheck > 0) {
                tieStatus.append(items.get(i-1) + " " + scoreTieCheck + " ");
            }*/
        }


    }

    protected String displayById(int id) {
        DB.dh.selectById(id);
        retrieveAllRows();
        return DB.sb.toString();
    }

    protected void retrieveAllRows() {
        List<Integer> idList = DB.dh.getIdList();
        List<String> names = DB.dh.getNameList();
        Log.v("MainActivity", idList.toString());
        Log.v("MainActivity", names.toString());
        int i = 0;
        DB.sb = new StringBuilder();
        for (String name : names) {
            Integer id = idList.get(i++);
            DB.sb.append(id + "." + name + "\n");
        }
    }

    public void readFile() {
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

            readingResult.setText(output);
            String msg = "read " + output + " from file " + fileInfo;
            Log.d(TAG, msg);
            //Toast.makeText(ResultActivity.this, msg, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStop() {
        super.onStop();
        DB.dh.update(1, "Manee 0 0 0");
        DB.dh.update(2, "Mana 0 0 0");
        DB.dh.update(3, "Choojai 0 0 0");
        DB.dh.update(4, "Piti 0 0 0");
        DB.dh.update(5, "Veera 0 0 0");
        DB.dh.update(6, "Pet 0 0 0");
    }
}
