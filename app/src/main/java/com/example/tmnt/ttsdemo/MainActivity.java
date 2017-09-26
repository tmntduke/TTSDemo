package com.example.tmnt.ttsdemo;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private TextToSpeech textToSpeech;
    private File file;
    @InjectView(R.id.edit)
    EditText edit;
    @InjectView(R.id.btn1)
    Button btn1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(Locale.US);
                    if (result != TextToSpeech.LANG_COUNTRY_AVAILABLE && result != TextToSpeech.LANG_AVAILABLE) {
                        Toast.makeText(MainActivity.this, "this is language not support", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        file = new File("/mnt/sdcard/sound.wav");
    }

    @OnClick({R.id.btn1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                textToSpeech.speak(edit.getText().toString(), TextToSpeech.QUEUE_ADD, null);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.shutdown();
        }
    }
}
