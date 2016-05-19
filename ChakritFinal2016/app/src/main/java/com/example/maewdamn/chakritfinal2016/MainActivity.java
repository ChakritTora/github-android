package com.example.maewdamn.chakritfinal2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity {

    Random rand, randName1, randName2;
    int random, whichDice, randomName1, randomName2, recentClick, recent, name1, name2;
    static String RECENT_PLAYER = "recentPlayer";
    static String NAME1 = "name1";
    static String NAME2 = "name2";
    String filename = "stats.txt", fileInfo;
    public static final String TAG = MainActivity.class.getSimpleName();
    ImageButton dice1, dice2;
    EditText dicevalue1, dicevalue2;
    TextView randomNameTextView1, randomNameTextView2, result;
    //RadioGroup radioGroup;
    RadioButton yesRadio, noRadio;
    Intent intent, intent_result;
    String resultString;
    View.OnClickListener yes_listener, no_listener;

    ArrayList<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recentClick = 1;

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

        randomNameTextView1 = (TextView) findViewById(R.id.player1Name);
        randomNameTextView2 = (TextView) findViewById(R.id.player2Name);

        randName1 = new Random();
        randomName1 = randName1.nextInt(6);
        randomNameTextView1.setText(items.get(randomName1));

        randName2 = new Random();

        for (;;) {
            randomName2 = randName2.nextInt(6);
            if (randomName1 != randomName2) {
                randomNameTextView2.setText(items.get(randomName2));
                break;
            }
        }



        dice1 = (ImageButton) findViewById(R.id.dice1);
        dice2 = (ImageButton) findViewById(R.id.dice2);
        dicevalue1 = (EditText) findViewById(R.id.dicevalue1);
        dicevalue2 = (EditText) findViewById(R.id.dicevalue2);

        result = (TextView) findViewById(R.id.result);

        TextWatcher tw = new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    String scorePlayer1 = dicevalue1.getText().toString();
                    String scorePlayer2 = dicevalue2.getText().toString();

                    if (scorePlayer1.matches("")) {

                    } else if (scorePlayer2.matches("")) {

                    } else {
                        updateResult(dicevalue1, dicevalue2);
                    }

                    if (scorePlayer1.matches("")) {

                    } else {
                        updateDice(dicevalue1, 1);
                        recentClick = 1;
                    }

                    if (scorePlayer2.matches("")) {

                    } else {
                        updateDice(dicevalue2, 2);
                        recentClick = 2;
                    }

                }
            }
        };


        dicevalue1.addTextChangedListener(tw);
        dicevalue2.addTextChangedListener(tw);

        intent = new Intent(this, NameActivity.class);
        intent_result = new Intent(this, ResultActivity.class);

        /*radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case 1:
                        //EditText editText = (EditText) findViewById(R.id.edit_message);
                        //String message = editText.getText().toString();
                        intent.putExtra(RECENT_PLAYER, recentClick);
                        startActivity(intent);

                        //Toast.makeText(getApplicationContext(), "Yes", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:

                        //Toast.makeText(getApplicationContext(), "No", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });*/

        yesRadio = (RadioButton) findViewById(R.id.yesRadioButton);
        noRadio = (RadioButton) findViewById(R.id.noRadioButton);

        yes_listener = new View.OnClickListener(){
            public void onClick(View v) {
                intent.putExtra(RECENT_PLAYER, recentClick);
                intent.putExtra(NAME1, randomName1);
                intent.putExtra(NAME2, randomName2);
                startActivity(intent);
            }
        };

        no_listener = new View.OnClickListener(){
            public void onClick(View v) {
                startActivity(intent_result);
                writeFile();
            }
        };

        yesRadio.setOnClickListener(yes_listener);
        noRadio.setOnClickListener(no_listener);

        fileInfo = getFileStreamPath(filename).getAbsolutePath();

        DB.dh = new DBHelper(this);
        DB.dh.insert(1, "Manee 0 0 0");
        DB.dh.insert(2, "Mana 0 0 0");
        DB.dh.insert(3, "Choojai 0 0 0");
        DB.dh.insert(4, "Piti 0 0 0");
        DB.dh.insert(5, "Veera 0 0 0");
        DB.dh.insert(6, "Pet 0 0 0");
    }



    public void diceClick(View view) {

        String scorePlayer1 = dicevalue1.getText().toString();
        String scorePlayer2 = dicevalue2.getText().toString();

        if (view == findViewById(R.id.dice1)) {
            whichDice = 1;

            dice1 = (ImageButton) findViewById(R.id.dice1);

            rand = new Random();
            random = rand.nextInt(6) + 1;
            dicevalue1.setText(String.valueOf(random));

            updateDice(dicevalue1, whichDice);
            recentClick = 1;

        } else if (view == findViewById(R.id.dice2)) {
            whichDice = 2;

            dice2 = (ImageButton) findViewById(R.id.dice2);

            rand = new Random();
            random = rand.nextInt(6) + 1;
            dicevalue2.setText(String.valueOf(random));

            updateDice(dicevalue2, whichDice);
            recentClick = 2;

        }

        if (!scorePlayer1.matches("") && scorePlayer2.matches("")) {
            updateResult(dicevalue1, dicevalue2);
        }
    }

    private void updateDice(EditText diceValue, int whichDice) {
        int value = Integer.parseInt(diceValue.getText().toString());

        if (whichDice == 1) {
            if (value == 1) {
                dice1.setImageResource(R.drawable.dice_one);

            } else if (value == 2) {
                dice1.setImageResource(R.drawable.dice_two);

            } else if (value == 3) {
                dice1.setImageResource(R.drawable.dice_three);

            } else if (value == 4) {
                dice1.setImageResource(R.drawable.dice_four);

            } else if (value == 5) {
                dice1.setImageResource(R.drawable.dice_five);

            } else if (value == 6) {
                dice1.setImageResource(R.drawable.dice_six);

            } else {
                dice1.setImageResource(R.drawable.dice_black_104);

            }

        } else if (whichDice == 2){
            if (value == 1) {
                dice2.setImageResource(R.drawable.dice_one);

            } else if (value == 2) {
                dice2.setImageResource(R.drawable.dice_two);

            } else if (value == 3) {
                dice2.setImageResource(R.drawable.dice_three);

            } else if (value == 4) {
                dice2.setImageResource(R.drawable.dice_four);

            } else if (value == 5) {
                dice2.setImageResource(R.drawable.dice_five);

            } else if (value == 6) {
                dice2.setImageResource(R.drawable.dice_six);

            } else {
                dice2.setImageResource(R.drawable.dice_black_104);

            }
        }
    }

    private void updateResult(EditText value1, EditText value2) {
        int value_1 = Integer.parseInt(value1.getText().toString());
        int value_2 = Integer.parseInt(value2.getText().toString());

        if (value_1 > value_2) {
            result.setText(items.get(randomName1) + " beats " + items.get(randomName2));

            String getResult = displayById(randomName1+1);
            String[] resultPart = getResult.split(" ");
            int scoreWinUpdate = Integer.parseInt(resultPart[1]) + 1;
            DB.dh.update(randomName1+1, resultPart[0] + " " + scoreWinUpdate + " " + resultPart[2] + " " + resultPart[3]);

            String getResultLose = displayById(randomName2+1);
            String[] resultPartLose = getResultLose.split(" ");
            int scoreLoseUpdate = Integer.parseInt(resultPartLose[2]) + 1;
            DB.dh.update(randomName2+1, resultPartLose[0] + " " + resultPartLose[1] + " " + scoreLoseUpdate + " " + resultPartLose[3]);


            Log.d("Score", "Win: " + items.get(randomName1) + " " + scoreWinUpdate + " " + resultPart[2] + " " + resultPart[3]);
            Log.d("Score", "Lose: " + items.get(randomName2) + " " + resultPartLose[1] + " " + scoreLoseUpdate + " " + resultPartLose[3]);


        } else if (value_2 > value_1) {
            result.setText(items.get(randomName2) + " beats " + items.get(randomName1));

            String getResult = displayById(randomName2+1);
            String[] resultPart = getResult.split(" ");
            int scoreWinUpdate = Integer.parseInt(resultPart[1]) + 1;
            DB.dh.update(randomName2+1, resultPart[0] + " " + scoreWinUpdate + " " + resultPart[2] + " " + resultPart[3]);

            String getResultLose = displayById(randomName1+1);
            String[] resultPartLose = getResultLose.split(" ");
            int scoreLoseUpdate = Integer.parseInt(resultPartLose[2]) + 1;
            DB.dh.update(randomName1+1, resultPartLose[0] + " " + resultPartLose[1] + " " + scoreLoseUpdate + " " + resultPartLose[3]);


            Log.d("Score", "Win: " + items.get(randomName2) + " " + scoreWinUpdate + " " + resultPart[2] + " " + resultPart[3]);
            Log.d("Score", "Lose: " + items.get(randomName1) + " " + resultPartLose[1] + " " + scoreLoseUpdate + " " + resultPartLose[3]);

        } else if (value_1 == value_2){
            result.setText(items.get(randomName1) + " ties with " + items.get(randomName2));

            String getResult = displayById(randomName1+1);
            String[] resultPart = getResult.split(" ");
            int scoreTieUpdate = Integer.parseInt(resultPart[2]) + 1;
            DB.dh.update(randomName1+1, resultPart[0] + " " + resultPart[2] + " " + resultPart[2] + " " + scoreTieUpdate);

            String getResultLose = displayById(randomName2+1);
            String[] resultPartLose = getResultLose.split(" ");
            int scoreTieUpdate2 = Integer.parseInt(resultPartLose[2]) + 1;
            DB.dh.update(randomName2+1, resultPartLose[0] + " " + resultPartLose[1] + " " + resultPartLose[2] + " " + scoreTieUpdate2);


            Log.d("Score", "Tie: " + items.get(randomName1) + " " + resultPart[2] + " " + resultPart[2] + " " + scoreTieUpdate);
            Log.d("Score", "Tie: " + items.get(randomName2) + " " + resultPartLose[1] + " " + resultPartLose[2] + " " + scoreTieUpdate2);

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

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("NamePlayer1", randomName1);
        savedInstanceState.putInt("NamePlayer2", randomName2);
        savedInstanceState.putInt("DicePlayer1", Integer.parseInt(dicevalue1.getText().toString()));
        savedInstanceState.putInt("DicePlayer2", Integer.parseInt(dicevalue2.getText().toString()));
        savedInstanceState.putString("Result", result.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        randomNameTextView1.setText(items.get(savedInstanceState.getInt("NamePlayer1")));
        randomNameTextView2.setText(items.get(savedInstanceState.getInt("NamePlayer2")));
        dicevalue1.setText(savedInstanceState.getInt("DicePlayer1"));
        dicevalue2.setText(savedInstanceState.getInt("DicePlayer2"));
        result.setText(savedInstanceState.getString("Result"));
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                resultString = data.getStringExtra("result");
                recent = Integer.parseInt(data.getStringExtra("recent"));
                name1 = Integer.parseInt(data.getStringExtra("name1"));
                name2 = Integer.parseInt(data.getStringExtra("name2"));
                if (recent == 1) {
                    randomNameTextView1.setText(resultString);
                    randomNameTextView2.setText(items.get(name2).toString());

                } else if (recent == 2) {
                    randomNameTextView1.setText(items.get(name1).toString());
                    randomNameTextView2.setText(resultString);

                }
            }
        }
    }


    public void writeFile() {
        try {
            FileOutputStream fileOut = openFileOutput(filename, MODE_PRIVATE);
            OutputStreamWriter outputWriter = new OutputStreamWriter(fileOut);
            String data = result.getText().toString();
            outputWriter.write(data);
            outputWriter.close();
            fileOut.close();
            String msg = "write " + data + " to file " + fileInfo;
            Log.d(TAG, msg);
            //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}