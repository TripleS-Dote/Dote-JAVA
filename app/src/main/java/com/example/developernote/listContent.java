package com.example.developernote;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class listContent extends LinearLayout {

    EditText edtInput;
    Button btnsetInput;
    Button btnToDoList;
    TextView showInput;

    TextView text_server;

    //listContent 선언 (
    @SuppressLint("ResourceAsColor")
    public listContent(Context context) {
        super(context);
        edtInput = new EditText(context);
        btnsetInput = new Button(context);
        showInput = new TextView(context);

        btnToDoList = findViewById(R.id.btnToDoList);
        text_server = findViewById(R.id.text_server);

        this.setBackgroundResource(R.drawable.listcontent);
        this.setOrientation(HORIZONTAL);

        btnsetInput.setText("추가");
        btnsetInput.setWidth(200);
        edtInput.setWidth(800);

        showInput.setHeight(100);
        showInput.setGravity(Gravity.CENTER_VERTICAL);
        showInput.setTextColor(Color.parseColor("#000000"));

        this.addView(edtInput);
        this.addView(btnsetInput);

        //버튼을 눌렀을 때 목표 입력하기 내용있으면 추가
        btnsetInput.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputText = edtInput.getText().toString();
                if(inputText.equals("")){
                    Toast.makeText(context,"목표를 입력하세요",Toast.LENGTH_SHORT).show();
                }else{
                    removeEdtBtn();
                    addTextView(inputText);
                }
            }
        });


        //todolist를 불러오는 과정
//        btnToDoList.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    int portNumber = 80;5
//                    Socket sock = new Socket("192.168.0.147", portNumber); // 소켓 객체 만들기
//
//                    ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
//                    text_server.setText((CharSequence) instream);
//                    //sock.close();
//                } catch(Exception ex) {
//                    ex.printStackTrace();
//                }
//            }
//        });
    }

    //removeEdtBtn선언 (뷰 제거하는 동작(edtInput, btnsetInput))
    public void removeEdtBtn(){
        this.removeView(edtInput);
        this.removeView(btnsetInput);
    }

    //addTextView선언 (인자로 넣은 text를 뷰에 추가함)
    public void addTextView(String inputText){
        showInput.setText("ㆍ"+inputText);
        this.addView(showInput);
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(inputText);
            }
        }).start();
    }

    public void send(String data){
        try {
            int portNumber = 80;
            Socket sock = new Socket("192.168.0.147", portNumber); //소켓 객체 만들기 localhost->ip주소
            //printClientLog("소켓 연결함.");

            ObjectOutputStream outstream = new ObjectOutputStream(sock.getOutputStream());
            //소켓 객체로 데이터 보내기
            outstream.writeObject(data);
            outstream.flush();          //전송
            //printClientLog("데이터 전송함.");

//            ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
//            text_server.setText((CharSequence) instream);

            //ObjectInputStream instream = new ObjectInputStream(sock.getInputStream());
            //printClientLog("서버로부터 받음 : " + instream.readObject());
            //sock.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
