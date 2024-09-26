package com.example.developernote;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import com.kakao.sdk.auth.model.OAuthToken;
//import com.kakao.sdk.common.KakaoSdk;
//import com.kakao.sdk.user.UserApiClient;
//import com.kakao.sdk.user.model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class activity_login extends AppCompatActivity {

    Button button_login, button_kakao;
    EditText editText_username, editText_password;

    SQLiteHelper sqLiteHelper;  //로그인 DB 테스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        button_login = findViewById(R.id.button_login);
        button_kakao = findViewById(R.id.button_kakao);
        editText_username = findViewById(R.id.editText_username);
        editText_password = findViewById(R.id.editText_password);

        //KakaoSdk.init(this, "3ec63c1c43c7de3c12e0cbf59072da0d");

//        Function2<OAuthToken, Throwable, Unit> callback = new Function2<OAuthToken, Throwable, Unit>() {
//            @Override
//            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
//                updatekakaoLoginUi();
//                return null;
//            }
//        };

//        button_kakao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //카카오톡이 핸드폰에 설치되어 있는지 확인
//                if(UserApiClient.getInstance().isKakaoTalkLoginAvailable(activity_login.this)) {
//                    UserApiClient.getInstance().loginWithKakaoTalk(activity_login.this, callback);
//                } else {        //카카오톡이 휴대폰에 설치되어 있지 않으면
//                    UserApiClient.getInstance().loginWithKakaoAccount(activity_login.this, callback);
//                }
//            }
//        });

        SQLiteHelper sqLiteHelper = new SQLiteHelper(this);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sqLiteHelper.findID(editText_username.getText().toString())
                        .equals(editText_username.getText().toString())) {
                    if(sqLiteHelper.findPW(editText_username.getText().toString())
                            .equals(editText_password.getText().toString())) {
                        Intent intent = new Intent(activity_login.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "비밀번호가 틀립니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "등록되지 않은 회원입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//    private void updatekakaoLoginUi() {
//        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
//            @Override
//            public Unit invoke(User user, Throwable throwable) {
//                //로그인 되어 있으면
//                if(user != null) {
//                    //닉네임
//                    Log.d(TAG, "invoke : nickname" + user.getKakaoAccount().getProfile().getNickname());
//                } else {
//                    Log.d(TAG, "로그인 안됨");
//                }
//                return null;
//            }
//        });
//    }
}