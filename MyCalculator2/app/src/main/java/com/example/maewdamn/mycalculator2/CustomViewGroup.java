package com.example.maewdamn.mycalculator2;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by Maewdamn on 30/3/2559.
 */
public class CustomViewGroup extends FrameLayout {

    private Button btnhello;


    public CustomViewGroup(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
    }

    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
    }

    @TargetApi(21)
    public CustomViewGroup(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initInflate();
        initInstances();
    }

    private void initInflate() {
        inflate(getContext(), R.layout.sample_layout, this);
    }

    private void initInstances() {
        btnhello = (Button) findViewById(R.id.nameCustomViewGroup);
    }

    protected void setButtonText(String text) {
        btnhello.setText(text);
    }
}
