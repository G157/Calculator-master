package com.example.yuko.calculator;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.PlaybackParams;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.view.View;import android.view.View.OnClickListener;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    SoundPool soundPool;    // 効果音を鳴らす本体（コンポ）
    int mp3a;
    TextView textView;
    EditText editText;
    Button button;
    Button play;
    MediaPlayer mp = null;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // mp.start();

            textView.setText(editText.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(this, R.raw.summer);


        /*if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        } else {
            AudioAttributes attr = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setAudioAttributes(attr)
                    .setMaxStreams(1)
                    .build();
        }

        // ③ 読込処理(CDを入れる)
        mp3a = soundPool.load(this, R.raw.notanomori_201603251247210001, 1);
        soundPool.play(mp3a, 1.0F, 1.0F, 0, 0, 1.0F);*/
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        textView = (TextView) findViewById(R.id.textview);

        textView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        editText = (EditText) findViewById(R.id.edittext);
        editText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);

        play = (Button) findViewById(R.id.button);
        play.setOnClickListener(this);

        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);


    }

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;

            if (isOperatorKeyPushed == true) {
                editText.setText(button.getText());
            } else {
                editText.append(button.getText());
            }

            isOperatorKeyPushed = false;
        }
    };

    int recentOperator = R.id.button_equal; // 最近押された計算キー
    double result;  // 計算結果
    boolean isOperatorKeyPushed;    // 計算キーが押されたことを記憶

    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());
            if (recentOperator == R.id.button_equal) {
                result = value;
                ((pl.droidsonroids.gif.GifImageView)findViewById(R.id.gifImageView)).setImageResource(R.drawable.matu);
            } else {
                result = calc(recentOperator, result, value);
                ((pl.droidsonroids.gif.GifImageView)findViewById(R.id.gifImageView)).setImageResource(R.drawable.nukos);
                editText.setText(String.valueOf(result));

            }

            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };


    double calc(int operator, double value1, double value2) {
        switch (operator) {
            case R.id.button_add:
                return value1 + value2;

            case R.id.button_subtract:
                return value1 - value2;
            case R.id.button_multiply:
                return value1 * value2;
            case R.id.button_divide:
                return value1 / value2;
            default:
                return value1;
        }
    }

    public void setFontType(TextView txt) {
        txt.setTypeface(Typeface.createFromAsset(getAssets(), "Qarmic_sans_Abridged.ttf"));
    }


    @Override
    public void onClick(View v) {
        if (mp.isPlaying()) { //再生中
            play.setText("clear");
            mp.stop();

            try {
                mp.prepare();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else { //停止中
            play.setText("夏ﾀﾞﾈ(*´∀｀)");
            mp.seekTo(0); //
            mp.start();
        }
    }
}