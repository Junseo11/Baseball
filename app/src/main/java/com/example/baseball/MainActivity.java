package com.example.baseball;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {


    EditText answer;
    Button start, exit, correct;
    TextView life, ran,result;

    int q[]=new int[3];
    int userInput[]=new int[3];

    int count=10;
    int strike =0;
    int ball=0;
    String list="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start=findViewById(R.id.startbtn);
        exit=findViewById(R.id.exitbtn);
        answer=findViewById(R.id.answer);
        correct=findViewById(R.id.correct);
        life=findViewById(R.id.life_txt);
        ran=findViewById(R.id.ran_txt);
        result=findViewById(R.id.resulttxt);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                randomNumber();
                viewMode("start");

                Toast.makeText(MainActivity.this, "게임시작!", Toast.LENGTH_SHORT).show();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "초기화 됬습니다!", Toast.LENGTH_SHORT).show();

                viewMode("end");
                retry();
            }

        });

        correct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                compare();
                strike=0;
                ball=0;
            }
        });

    }

    public void randomNumber(){

        HashSet <Integer> set = new HashSet<>();

        Random random= new Random();
        int c=0;

        for(int i=0; i<3; ++i){
            int ranNum=random.nextInt(9)+1;
            set.add(ranNum);
        }

        Iterator ite= set.iterator();

        while(ite.hasNext()){

            q[c]= (int) ite.next();
            ++c;
        }




    }


    public void compare(){
        Scanner input= new Scanner(System.in);
        --count;


        life.setText("남은기회 "+count+"번");


        String in=answer.getText().toString();



        userInput[0] =Integer.parseInt(in.substring(0,1));
        userInput[1] =Integer.parseInt(in.substring(1,2));
        userInput[2] =Integer.parseInt(in.substring(2,3));


        for(int i=0; i<3; ++i){

            for(int j=0; j<3; ++j){

                if(q[i]==userInput[j]){
                    if(i==j){
                        ++strike;
                    }
                    else{
                        ++ball;
                    }
                }
                if(strike==3){
                    Toast.makeText(this, "성공입니다!", Toast.LENGTH_SHORT).show();
                    ran.setText("정답은 "+q[0]+q[1]+q[2]);
                }

                else if(count==0){
                    Toast.makeText(this, "기회 소진!", Toast.LENGTH_SHORT).show();
                    ran.setText("정답은 "+q[0]+" "+q[1]+" "+q[2]);
                    viewMode("end");
                }
                else{
                    Toast.makeText(this, "오답!", Toast.LENGTH_SHORT).show();
                    ran.setText("strike:"+strike+"ball:"+ ball);
                }
            }
        }
        showResult(in);
    }

    public void retry(){
        life.setText("남은기회 "+10+"번");
        ran.setText("");
        count=10;
        strike=0;
        ball=0;
        list="";
        result.setText("");

    }

    public void showResult(String in){
        list=in+" strike: "+strike+ "ball: "+ball;
        result.append(list+"\n");

    }

    public void viewMode(String mode){
        if(mode.equals("start")){
            start.setEnabled(false);
            exit.setEnabled(true);
            correct.setEnabled(true);

        }
        else if(mode.equals("end")){
            start.setEnabled(true);
            exit.setEnabled(false);
            correct.setEnabled(false);
        }

    }




}