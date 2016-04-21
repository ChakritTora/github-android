package com.example.maewdamn.mycalculator2;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstNum, secondNum;
    String resultString;
    TextView result;
    Button button;
    RadioGroup operator;
    Bundle bundle;
    Coordinate c1;
    CoordinateSerializable c2;
    CoordinateParcelable c3;
    double resultNum, first, second;
    CustomViewGroup viewGroup1, viewGroup2;


    public class Coordinate {
        public int x, y, z;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        button = (Button) findViewById(R.id.button);
        firstNum = (EditText) findViewById(R.id.firstNum);
        secondNum = (EditText) findViewById(R.id.secondNum);
        operator = (RadioGroup) findViewById(R.id.operator);
        result = (TextView) findViewById(R.id.result);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        //Toast.makeText(MainActivity.this, "Width = " + width + ", Height = " + height, Toast.LENGTH_SHORT).show();


        button.setOnClickListener(this);
        //switch1.setOnCheckedChangeListener(changeListener);

        initInstances();
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();
    }

    public void onClick(View view) {
        if (view == button) {
            try {
                first = Double.parseDouble(firstNum.getText().toString());

            } catch (NumberFormatException e1) {
                showToast("Please enter only a number");
            }

            try {
                second = Double.parseDouble(secondNum.getText().toString());

            } catch (NumberFormatException e2) {
                showToast("Please enter only a number");
            }

            //long tStart = System.currentTimeMillis();
            //Log.d("Calculation", "computation time = " + 0.0);


            switch (operator.getCheckedRadioButtonId()) {
                case R.id.plus:
                    resultNum = first + second;
                    break;

                case R.id.minus:
                    resultNum = first - second;
                    break;

                case R.id.multiple:
                    resultNum = first * second;
                    break;

                case R.id.devide:
                    try {
                        resultNum = first / second;
                        if (resultNum == Double.POSITIVE_INFINITY || resultNum == Double.NEGATIVE_INFINITY) throw new ArithmeticException();
                        break;

                    } catch (ArithmeticException ae) {
                        Toast toast = Toast.makeText(MainActivity.this, "Please devide by a non-zero number", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        resultNum = 0;

                    }
            }

            //long tEnd = System.currentTimeMillis();
            //long tTime = tEnd - tStart;
            //double elapsedSeconds = tTime / 1000.0;
            //Log.d("Calculation", "computation time = " + elapsedSeconds);


            result.setText("= " + resultNum);

            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra("resultNum", resultNum);

            c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            c2 = new CoordinateSerializable();
            c2.x = 5;
            c2.y = 10;
            c2.z = 20;
            intent.putExtra("cSerializable", c2);

            c3 = new CoordinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, 12345);
        }
    }

    /*@Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("num1", firstNum.getText().toString());
        outState.putString("num2", secondNum.getText().toString());
        outState.putString("result", result.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        firstNum.setText(savedInstanceState.getString("num1"));
        secondNum.setText(savedInstanceState.getString("num2"));
        result.setText(savedInstanceState.getString("result"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345) {
            if (resultCode == RESULT_OK) {
                resultString = data.getStringExtra("result");
                Toast.makeText(MainActivity.this, resultString, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast toast = Toast.makeText(MainActivity.this, "Setting selected", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return true;
        }

        if (id == R.id.action_refresh) {
            Toast toast = Toast.makeText(MainActivity.this, "Refresh selected", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initInstances() {
        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

        viewGroup1.setButtonText("Hello");
        viewGroup2.setButtonText("World");

    }
}
