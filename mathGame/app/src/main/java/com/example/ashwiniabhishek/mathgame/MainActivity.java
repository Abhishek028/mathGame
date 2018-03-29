package com.example.ashwiniabhishek.mathgame;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {
    TextView textView,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9;
    String a,b;
    Button button;
    boolean flag1=true,setSumFlag = true;
    GridLayout gridLayout;
    int correct=0,total=0;
    CountDownTimer countDownTimer = new CountDownTimer(30000+100,1000) {
        @Override
        public void onTick(long l) {
            long sec = l/1000;
            textView6.setText(sec+"s");
        }

        @Override
        public void onFinish() {
            textView8 = findViewById(R.id.textView8);
            textView6.setText("0s");
            setSumFlag = false;
            textView9.setText("Your score "+textView8.getText());
            button  = findViewById(R.id.button);
            button.setVisibility(View.VISIBLE);
           // textView9.setVisibility(View.GONE);
        }
    };

    void setVisibilityOfText(){
        textView = findViewById(R.id.textView);
        textView9 = findViewById(R.id.textView9);
        if(flag1){
            textView.setVisibility(View.VISIBLE);
            gridLayout = findViewById(R.id.gridLayout);
            textView6 = findViewById(R.id.textView6);
            textView7 = findViewById(R.id.textView7);
            textView8 = findViewById(R.id.textView8);
            textView6.setVisibility(View.GONE);
            textView7.setVisibility(View.GONE);
            textView8.setVisibility(View.GONE);
            gridLayout.setVisibility(View.GONE);
            textView9.setVisibility(View.GONE);
            flag1=false;
        }
        else{
            textView.setVisibility(View.GONE);
            textView6.setVisibility(View.VISIBLE);
            textView7.setVisibility(View.VISIBLE);
            textView8.setVisibility(View.VISIBLE);
            gridLayout.setVisibility(View.VISIBLE);
            flag1=true;
        }


    }
    void setOptions(){
        Random randomOption  = new Random();
        Random randomAnswers  = new Random();
        int option = randomOption.nextInt(4);
        TextView correctOption;
        for(int i=0;i<4;i++){
            correctOption =(TextView)(gridLayout.getChildAt(i));
            if(option == Integer.parseInt(((String)((gridLayout.getChildAt(i)).getTag())))){
                correctOption.setText(Integer.toString(Integer.parseInt(a)+Integer.parseInt(b)));
            }
            else{
                int answer  = randomAnswers.nextInt(90);
                while(answer == Integer.parseInt(a)+Integer.parseInt(b))
                    answer  = randomAnswers.nextInt(90);
                correctOption.setText(Integer.toString(answer));
            }
        }
    }
    public void setSum(){
        if(setSumFlag) {

            Random randomNumber1 = new Random();
            Random randomNumber2 = new Random();
            textView7.setText(randomNumber1.nextInt(30) + " + " + randomNumber2.nextInt(50));
            StringTokenizer stringTokenizer = new StringTokenizer((String)textView7.getText()," ");
            a = (String)stringTokenizer.nextElement();
            stringTokenizer.nextElement();
            b = (String)stringTokenizer.nextElement();

        }
    }
    public  void checkOption(View view){
        if(setSumFlag) {
            textView8 = findViewById(R.id.textView8);
            textView9 = findViewById(R.id.textView9);
            TextView option = (TextView) view;
            int tappedOption = Integer.parseInt((String) option.getText());
            if (Integer.parseInt(a) + Integer.parseInt(b) == tappedOption) {
                textView9.setText("CORRECT");
                correct++;
                total++;
            } else {
                textView9.setText("WRONG");
                total++;
            }
            textView9.setVisibility(View.VISIBLE);
            textView8.setText(correct+"/"+total);
            setSum();
            setOptions();
        }
        else{
            textView9.setText("Your score "+textView8.getText());

        }

    }

    public void playAgain(View view){
        textView8  = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        button  = findViewById(R.id.button);
        button.setVisibility(View.GONE);
        setSumFlag = true;
        setSum();
        setOptions();
        textView8.setText("0/0");
        correct=0;
        total=0;
        textView9.setVisibility(View.GONE);
        countDownTimer.start();
    }


    public void play(View view){
         setVisibilityOfText();
         countDownTimer.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setVisibilityOfText();
        button  = findViewById(R.id.button);
        button.setVisibility(View.GONE);
        setSum();
        setOptions();

    }
}
