package com.android.utils;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dxs
 * @time: 2020/6/2
 * @Email: duanxuesong12@126.com
 */
public class ConfigUtils {

    public static File findConfigFile(String file, Context mContext){
        if(file==null) return null;
        StorageManager mStorageManager = (StorageManager)mContext.getSystemService(Context.STORAGE_SERVICE);
        //0.Absolute

        Log.d("hjc","findConfigFile:"+file);
        if(file.startsWith("/")||file.startsWith("\\")){
            return new File(file);
        }

        File existedFile = null;

        //1.External SDCard
        if(StorageUtils.getSDcardDir(mStorageManager)!=null){
            File cardfile = new File(StorageUtils.getSDcardDir(mStorageManager));
            existedFile = new File(cardfile,file);
            if(existedFile.exists())
                return existedFile;
        }

        //2.USB
        List<String> usbList = getAliveUsbPath(mContext);
        for(String usb : usbList){
            existedFile = new File(usb, file);
            if(existedFile.exists()){
                return existedFile;
            }
        }

        //3.Internal SDCard
        File sdDir = Environment.getExternalStorageDirectory();
        existedFile = new File(sdDir, file);
        if(existedFile.exists()){
            return existedFile;
        }
        //Not Found
        return null;
    }

    public static boolean hasConfigFile(String file, Context context){
        File searchFile = findConfigFile(file,context);
        return searchFile!=null&&searchFile.exists();
    }

    public static List<String> getAliveUsbPath(Context mContext){
        StorageManager mStorageManager = (StorageManager)mContext.getSystemService(Context.STORAGE_SERVICE);
        List<String> usbList = new ArrayList<String>();
        usbList = StorageUtils.getUsbPaths(mStorageManager);
        Log.d("hjc","======getAliveUsbPath:"+usbList);
        return usbList;
    }
}
