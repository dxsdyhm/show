package com.android.show;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.utils.SceduHelper;
import com.android.utils.SystemInfo;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.DeviceUtils;
import com.blankj.utilcode.util.RomUtils;
import com.blankj.utilcode.util.ShellUtils;

import java.util.Arrays;

/**
 * 主要实现两个需求
 * 1.下载文件到指定目录
 * 2.执行服务器命令行
 * --->请求参数携带设备信息
 * --->定期执行(是否可配置执行间隔)
 */
public class FullscreenActivity extends AppCompatActivity {

    String test="isRoot:%s\n isapproot:%s\n ri:%s\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        TextView tx=findViewById(R.id.fullscreen_content);
        //tx.setText(String.format(test, DeviceUtils.isDeviceRooted(), AppUtils.isAppRoot(), RomUtils.getRomInfo()));
        finish();
    }
}