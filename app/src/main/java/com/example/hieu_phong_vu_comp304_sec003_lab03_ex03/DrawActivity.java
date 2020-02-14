package com.example.hieu_phong_vu_comp304_sec003_lab03_ex03;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class DrawActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    int x=0;
    int y=0;
    int thick;
    int color=Color.RED;
    ImageView imageView;
    ArrayList<Path> paths=new ArrayList<Path>();
    ArrayList<Paint> paints=new ArrayList<Paint>();
    String[] thickness = { "20", "40", "60", "80","100" };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw_main);

        //add spinner values
        final Spinner spinnerThickness=(Spinner)findViewById(R.id.spinnerThickness);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, thickness);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerThickness.setAdapter(adapter);
        spinnerThickness.setOnItemSelectedListener(this);

        //get radio buttons value
        final RadioButton radioRed=(RadioButton)findViewById(R.id.radioButtonRed);
        final RadioButton radioYellow=(RadioButton)findViewById(R.id.radioButtonYellow);
        final RadioButton radioCyan=(RadioButton)findViewById(R.id.radioButtonCyan);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                color=(radioRed.isChecked())? Color.RED
                        :(radioYellow.isChecked())? Color.YELLOW
                        :(radioCyan.isChecked())? Color.CYAN
                        :Color.WHITE;
            }
        });

        imageView=(ImageView)findViewById(R.id.imageView);
        updateCoord();

        final Button buttonRight= (Button) findViewById(R.id.buttonRight);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Path p = new Path();
                p.moveTo(x,y);
                x+=thick;
                p.lineTo(x, y);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setColor(color);
                paint.setStrokeWidth(thick);
                paths.add(p);
                paints.add(paint);
                updateCoord();
                draw();
            }
        });

        final Button buttonDown= (Button) findViewById(R.id.buttonDown);
        buttonDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Path p = new Path();
                p.moveTo(x,y);
                y+=thick;
                p.lineTo(x, y);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setColor(color);
                paint.setStrokeWidth(thick);
                paths.add(p);
                paints.add(paint);
                updateCoord();
                draw();
            }
        });

        final Button buttonUp= (Button) findViewById(R.id.buttonUp);
        buttonUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Path p = new Path();
                p.moveTo(x,y);
                y-=thick;
                p.lineTo(x, y);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setColor(color);
                paint.setStrokeWidth(thick);
                paths.add(p);
                paints.add(paint);
                updateCoord();
                draw();
            }
        });

        final Button buttonLeft= (Button)findViewById(R.id.buttonLeft);
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Path p = new Path();
                p.moveTo(x,y);
                x-=thick;
                p.lineTo(x, y);
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeJoin(Paint.Join.MITER);
                paint.setColor(color);
                paint.setStrokeWidth(thick);
                paths.add(p);
                paints.add(paint);
                updateCoord();
                draw();
            }
        });

        final Button buttonClear=(Button)findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                x=0;
                y=0;
                paths.clear();
                paints.clear();
                updateCoord();
                draw();
            }
        });
    }

    void draw(){
        Bitmap bitmap = Bitmap.createBitmap(imageView.getWidth(), imageView.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        imageView.setImageBitmap(bitmap);

        for (int i=0;i<paths.size()&&i<paints.size();i++){
            canvas.drawPath(paths.get(i),paints.get(i));
        }
    }

    public void updateCoord(){
        final TextView textViewCoord=findViewById(R.id.textViewCoord);
        textViewCoord.setText("x:"+x+" y:"+y);
    }
    //get spinner value
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        thick=Integer.parseInt(String.valueOf(thickness[position]));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
