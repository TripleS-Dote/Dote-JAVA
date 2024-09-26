package com.example.developernote;
//developnote 작성 코드

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DevelopeNote extends AppCompatActivity implements View.OnTouchListener {

    static final int SLIDE_LEFT = -1;
    static final int SLIDE_RIGHT = 1;
    int currMotion = 0; // 왼쪽 슬라이드 : -1, 중립 : 0, 오른쪽 슬라이드 : 1 
    
    int currMonth; // 화면에 표시될 현재 월
    int currDay; // 화면에 표시될 현재 일

    TextView txtDay, txtMonth;
    LinearLayout layoutContent;

    Animation slideHideRight,slideHideLeft,slideShowRight,slideShowLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_develope_note);

        //날짜 설정
        currMonth = getDate("MM");
        currDay = getDate("dd");

        //날짜 출력
        txtMonth = (TextView) findViewById(R.id.txtMonth);
        txtMonth.setText(currMonth+"월");
        txtDay = (TextView) findViewById(R.id.txtDay);
        txtDay.setText(currDay+"일");
        layoutContent = (LinearLayout)findViewById(R.id.layoutDevelopeNoteContent);

        //왼쪽 오른쪽으로 이동
        slideHideRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_hide_toright);
        slideHideLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_hide_toleft);
        slideShowRight = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_show_toright);
        slideShowLeft = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_show_toleft);

        //animListener 호출
        slideHideRight.setAnimationListener(animListener);
        slideHideLeft.setAnimationListener(animListener);
        slideShowRight.setAnimationListener(animListener);
        slideShowLeft.setAnimationListener(animListener);

        layoutContent.setOnTouchListener(this::onTouch);

    }

    //onTouch 선언(터치가 일어났을 경우 모션을 정함)
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float touchX = 0f;

        if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
            touchX = motionEvent.getRawX();
            Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getRealSize(size);
            float screenCenterX = size.x/2;
            if(touchX > screenCenterX){
                currMotion = SLIDE_RIGHT;
            }else{
                currMotion = SLIDE_LEFT;
            }
            playAnim();
        }
        return false;
    }

    //palyAnim 선언(화면이 좌우로 움직일때의 모션을 정함)
    public void playAnim(){
        if(currMotion == SLIDE_RIGHT){
            if(currDay != 1){
                txtDay.startAnimation(slideHideLeft);
            }
        }else if(currMotion == SLIDE_LEFT){
            txtDay.startAnimation(slideHideRight);
        }
    }

    //getDate 선언.(시간을 얻어와서 출력함)
    public int getDate(String format){
        int result = 0;
        long currTime = System.currentTimeMillis();
        Date date = new Date(currTime);
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        result = Integer.parseInt(dateFormat.format(date));

        return result;
    }

    //onAnimationStart(좌우로 움직일때 날짜를 출력함)
    Animation.AnimationListener animListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if(currMotion == SLIDE_RIGHT){
                txtDay.startAnimation(slideShowLeft);
                txtDay.setText((++currDay)+"일");
                currMotion = 0;
            }else if(currMotion == SLIDE_LEFT){
                txtDay.startAnimation(slideShowRight);
                txtDay.setText((--currDay)+"일");
                currMotion = 0;
            }

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    };

}