package com.example.ys020.appjamtest;

import android.content.Context;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.net.wifi.p2p.WifiP2pManager.ERROR;

public class MainActivity extends AppCompatActivity {
    public Button _vibrator_btn;
    public TextToSpeech tts;
    TextView On, Off;
    Switch mSwitch;
    ImageView mbell_off, mbell_on;
    SoundPool mSound;
    int mDing;
    AudioManager mAm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _vibrator_btn = findViewById(R.id.vibrator_btn);
        mSwitch = findViewById(R.id.sw_btn);
        On = findViewById(R.id.textView2);
        Off = findViewById(R.id.textView3);
        On.setVisibility(TextView.INVISIBLE);
        mbell_off = findViewById(R.id.bell_off);
        mbell_on = findViewById(R.id.bell_on);
        mSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mDing = mSound.load(this, R.raw.ddiring, 1);
        //findViewById(R.id.sw_btn).setOnClickListener();


        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MediaPlayer player;


                if (b) {
                    On.setVisibility(TextView.VISIBLE);
                    Off.setVisibility(TextView.INVISIBLE);
                    mbell_off.setVisibility(ImageView.INVISIBLE);
                    mbell_on.setVisibility(ImageView.VISIBLE);
                    Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(300);
                    mSound.play(mDing, 1, 1, 0, 0, 1);
                    Toast.makeText(getApplicationContext(), "알림이 활성 되었습니다!", Toast.LENGTH_SHORT).show();


                } else {
                    On.setVisibility(TextView.INVISIBLE);
                    Off.setVisibility(TextView.VISIBLE);
                    mbell_off.setVisibility(ImageView.VISIBLE);
                    mbell_on.setVisibility(ImageView.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "알림이 비활성 되었습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    tts.setLanguage(Locale.KOREAN);
                }

            }
        });
        _vibrator_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "컴퓨터를 종료하세요", Toast.LENGTH_SHORT).show();

                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(1000);

                tts.setPitch(1.0f);
                tts.setSpeechRate(1.0f);
                tts.speak("엄크가 떴습니다 컴퓨터를 종료하세요!", TextToSpeech.QUEUE_FLUSH, null);

            }
        });

    }
}




