package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    Button btnstart;
    int total;
    TextView que;
    TextView countertextview;
    ArrayList<Integer> answerlisttag;
    int num1,num2,ans,j;
    Random random;
    int randomtag;
    TextView correction;
    CountDownTimer countDownTimer;
    Button ans1,ans2,ans3,ans4;
    ArrayList<String> otherans;
    int tag;
    TextView marks;
    int score;

    public void display(){
        otherans.clear();

        ans1 = findViewById(R.id.ans1);
        ans2 = findViewById(R.id.ans2);
        ans3 = findViewById(R.id.ans3);
        ans4 = findViewById(R.id.ans4);
        que = findViewById(R.id.que);
        num1 = random.nextInt(100);
        num2 = random.nextInt(100);
        random = new Random();
        que.setText(num1+"+"+num2);
        ans = num1 + num2;
        Log.i("tag", String.valueOf(tag));
        Log.i("randomtag", String.valueOf(randomtag));

        if(randomtag ==0){
            wronganswers();
            ans1.setText(Integer.toString(ans));
            ans2.setText(otherans.get(0));
            ans3.setText(otherans.get(1));
            ans4.setText(otherans.get(2));

        }else if(randomtag ==1){
            wronganswers();
            ans2.setText(Integer.toString(ans));
            ans1.setText(otherans.get(0));
            ans3.setText(otherans.get(1));
            ans4.setText(otherans.get(2));
        }else if(randomtag ==2){
            wronganswers();
            ans3.setText(Integer.toString(ans));
            ans2.setText(otherans.get(0));
            ans1.setText(otherans.get(1));
            ans4.setText(otherans.get(2));

        }else{
            wronganswers();
            ans4.setText(Integer.toString(ans));
            ans1.setText(otherans.get(0));
            ans2.setText(otherans.get(1));
            ans3.setText(otherans.get(2));
        }

        /*if(randomtag==answerlisttag.get(j-1)){

        }*/

        countDownTimer.cancel();
        countDownTimer.start();

    }
    public void wronganswers(){

        int i;
        for(i=0;i<3;i++) {
            int otheranswer;
            otheranswer = random.nextInt(ans) + 30;

            while (otheranswer == ans) {
                otheranswer = random.nextInt(ans) + 30;
            }
            otherans.add(String.valueOf(otheranswer));
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        otherans = new ArrayList<String>();
        countertextview = findViewById(R.id.countertextview);
        linearLayout = findViewById(R.id.linearlayout);
        correction = findViewById(R.id.correction);
        btnstart = findViewById(R.id.btnstart);
        btnstart.setVisibility(View.VISIBLE);
        random = new Random();
        marks = findViewById(R.id.marks);
        answerlisttag = new ArrayList<>();
        total = 0;
        j = 0;

    }
    public void start(View view){
        btnstart.setVisibility(View.INVISIBLE);
        linearLayout.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countertextview.setText(String.valueOf(millisUntilFinished/1000));
            }
            @Override
            public void onFinish() {
                score-=2;
                marks.setText(Integer.toString(score)+"/"+Integer.toString(total));
                countertextview.setText("-2 score");
            }
        }.start();
        answerlisttag.add(0);
        score = 0;
        display();
    }

    public void btnclick(View v) {
        //first display question then get tag
        j++;
        total++;
        tag = Integer.parseInt((String) v.getTag());

        if(answerlisttag.get(j-1)==tag){
            randomtag = random.nextInt(4);
            answerlisttag.add(randomtag);
            score++;
            marks.setText(Integer.toString(score)+"/"+Integer.toString(total));
            correction.setText("correct(+1)");
            display();
        }else{
            j--;
            score--;
            marks.setText(Integer.toString(score)+"/"+Integer.toString(total));
            correction.setText("Wrong(-1)");
        }


    }
}