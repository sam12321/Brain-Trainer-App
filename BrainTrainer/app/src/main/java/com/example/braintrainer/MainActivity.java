package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView timerTextView;
    TextView scoreTextView;
    TextView questionTextView;

    Button go;

    GridLayout gridLayout;

    Button option1;
    Button option2;
    Button option3;
    Button option4;

    int digit1,digit2,sum;
    int op1,op2,op3,op4;
    int correctAnswerLocation;
    int wrongAnswer;
    int numberOfTimesCorrect=0,totalQuestions=0;

    TextView resultTextView;
    Button playagain;

    @SuppressLint("SetTextI18n")
    public void resetQuestion(View view){

        Random rnd=new Random();
        digit1=rnd.nextInt(50);
        digit2=rnd.nextInt(50);
        sum=digit1+digit2;
        correctAnswerLocation=rnd.nextInt(4);
        questionTextView.setText(digit1+"+"+digit2);

        ArrayList<Integer> answers = new ArrayList<Integer>();

        for(int i=0;i<4;i++){

            if (i == correctAnswerLocation){

                answers.add(sum);

            }
            else{

                wrongAnswer=rnd.nextInt(100);
                while (wrongAnswer==sum)
                {
                    wrongAnswer=rnd.nextInt(100);
                }
                answers.add(wrongAnswer);
            }

        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view){

        go.setVisibility(View.GONE);
        timerTextView.setVisibility(View.VISIBLE);
        scoreTextView.setVisibility(View.VISIBLE);
        questionTextView.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
        CountDownTimer countDownTimer=new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String string=String.valueOf((int)millisUntilFinished/1000);

            timerTextView.setText(":"+string);

            }

            @Override
            public void onFinish() {

                timerTextView.setVisibility(View.GONE);
                scoreTextView.setVisibility(View.GONE);
                questionTextView.setVisibility(View.GONE);
                gridLayout.setVisibility(View.GONE);
                //go.setVisibility(View.VISIBLE);

                int sc=(Integer)numberOfTimesCorrect*100/totalQuestions;
                String st="Your score is: "+sc;
                resultTextView.setText(st);

                resultTextView.setVisibility(View.VISIBLE);
                playagain.setVisibility(View.VISIBLE);

            }
        }.start();

        resetQuestion(view);

    }

    public void answer(View view){

        if (Integer.toString(correctAnswerLocation).equals(view.getTag().toString())){

            Toast.makeText(MainActivity.this, "Correct!!", Toast.LENGTH_SHORT).show();
            totalQuestions=totalQuestions+1;
            numberOfTimesCorrect=numberOfTimesCorrect+1;

        }
        else{

            Toast.makeText(MainActivity.this, "Wrong!!", Toast.LENGTH_SHORT).show();
            totalQuestions=totalQuestions+1;

        }

        String str=numberOfTimesCorrect+"/"+totalQuestions;
        scoreTextView.setText(str);
        resetQuestion(view);


    }

    public void playAgain(View view){

        totalQuestions=0;
        numberOfTimesCorrect=0;
        scoreTextView.setText("0/0");
        resultTextView.setVisibility(View.GONE);
        playagain.setVisibility(View.GONE);
        go.setVisibility(View.VISIBLE);
       // start(view);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView=(TextView)findViewById(R.id.timerId);
        scoreTextView=(TextView)findViewById(R.id.scoreId);
        questionTextView=(TextView)findViewById(R.id.questionId);

        go=(Button)findViewById(R.id.button);

        gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        option1=(Button)findViewById(R.id.option1);
        option2=(Button)findViewById(R.id.option2);
        option3=(Button)findViewById(R.id.option3);
        option4=(Button)findViewById(R.id.option4);

        resultTextView=(TextView)findViewById(R.id.resultId);
        playagain=(Button)findViewById(R.id.playagain);




    }
}
