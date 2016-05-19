package com.example.maewdamn.chakritquiz2y2016;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class NameActivity extends ListActivity {

    ArrayList<String> items = new ArrayList<String>();
    Intent intent;
    String message;
    int recent_player;
    RadioGroup selectPlayer;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        //intent = getIntent();
        extras = getIntent().getExtras();
        recent_player = extras.getInt("recentPlayer");
        selectPlayer = (RadioGroup) findViewById(R.id.selectPlayer);

        if (recent_player == 1) {
            selectPlayer.check(R.id.p1Radio);
        } else {
            selectPlayer.check(R.id.p2Radio);
        }

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

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
    }

    public void onListItemClick(ListView parent, View v, int position, long id) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", items.get(position).toString());
        returnIntent.putExtra("recent", recent_player);
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
