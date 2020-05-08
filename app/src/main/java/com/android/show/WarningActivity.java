package com.android.show;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ThreadUtils;

import java.util.concurrent.TimeUnit;

/**
 * 主要实现两个需求
 * 1.下载文件到指定目录
 * 2.执行服务器命令行
 * --->请求参数携带设备信息
 * --->定期执行(是否可配置执行间隔)
 */
public class WarningActivity extends AppCompatActivity {

    private int code;
    private String msg=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_warning);
        frushUi(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        frushUi(intent);
    }

    private void frushUi(Intent intent){
        if(intent!=null){
            code=getIntent().getIntExtra("code",0);
            msg=getIntent().getStringExtra("msg");
            TextView tx=findViewById(R.id.fullscreen_content);
            Button button=findViewById(R.id.btn_ok);
            if(canUse()){
                button.setVisibility(View.VISIBLE);
                if(TextUtils.isEmpty(msg)){
                    tx.setText(R.string.warning_tips);
                }else {
                    tx.setText(msg);
                }
            }else {
                tx.setText(R.string.shutdown_tips);
                button.setVisibility(View.GONE);
                ThreadUtils.executeBySingleWithDelay(new ShutdownTask(),5, TimeUnit.SECONDS);
                CountDownTimer timer = new CountDownTimer(5000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        StringBuilder builder=new StringBuilder(getString(R.string.shutdown_tips));
                        builder.append("\n");
                        builder.append(""+millisUntilFinished/1000);
                        tx.setText(builder);
                    }

                    @Override
                    public void onFinish() {

                    }
                }.start();
            }

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        if(canUse()){
            super.onBackPressed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(canUse()){
            return super.onKeyDown(keyCode, event);
        }
        return true;
    }

    private boolean canUse(){
        return code!=1;
    }
}