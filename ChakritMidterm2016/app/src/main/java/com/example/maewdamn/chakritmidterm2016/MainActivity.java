package com.example.maewdamn.chakritmidterm2016;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random rand;
    int random, whichDice;
    String randomString;
    ImageButton dice1, dice2;
    EditText dicevalue1, dicevalue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = (ImageButton) findViewById(R.id.dice1);
        dice2 = (ImageButton) findViewById(R.id.dice2);
        dicevalue1 = (EditText) findViewById(R.id.dicevalue1);
        dicevalue2 = (EditText) findViewById(R.id.dicevalue2);

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
                    updateDice(dicevalue1, whichDice);
                    updateResult(dicevalue1, dicevalue2);

                }
            }
        };


        dicevalue1.addTextChangedListener(tw);
        dicevalue2.addTextChangedListener(tw);

    }







    public void diceClick(View view) {

        if (view == findViewById(R.id.dice1)) {
            whichDice = 1;

            dice1 = (ImageButton) findViewById(R.id.dice1);
            dicevalue1 = (EditText) findViewById(R.id.dicevalue1);

            rand = new Random();
            random = rand.nextInt(6) + 1;
            dicevalue1.setText(String.valueOf(random));

            updateDice(dicevalue1, whichDice);

        } else if (view == findViewById(R.id.dice2)) {
            whichDice = 2;

            dice2 = (ImageButton) findViewById(R.id.dice2);
            dicevalue2 = (EditText) findViewById(R.id.dicevalue2);

            rand = new Random();
            random = rand.nextInt(6) + 1;
            dicevalue2.setText(String.valueOf(random));

            updateDice(dicevalue2, whichDice);

        }

        updateResult(dicevalue1, dicevalue2);
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
        TextView result = (TextView) findViewById(R.id.result);

        if (value_1 > value_2) {
            result.setText("Player 1 beats Player 2");

        } else if (value_2 > value_1) {
            result.setText("Player 2 beats Player 1");

        } else if (value_1 == value_2){
            result.setText("Player 1 ties with Player 2");

        }
    }
}
