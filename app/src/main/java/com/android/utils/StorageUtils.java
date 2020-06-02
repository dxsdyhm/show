package com.android.utils;

import android.os.Environment;
import android.os.storage.DiskInfo;
import android.os.storage.StorageManager;
import android.os.storage.VolumeInfo;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 *StorageUtils is use to get correct storage path between ics and gingerbread.
 *add by lijiehong
 */ 
public class StorageUtils { 
    public static String TAG = "StorageUtils";
    public static String mUsbDirs;
    public static String mSDcardDir;

    
    /*
     * ??��?��a??SD?��1��??????
     */
    public static String getSDcardDir(StorageManager storageManager) {

        final List<VolumeInfo> volumes = storageManager.getVolumes();
        //Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo vol : volumes) {
            if (vol.getType() == VolumeInfo.TYPE_PUBLIC) {
                DiskInfo disk = vol.getDisk();
                if (disk != null) {
                    if (disk.isSd()) {
                        //mSDcardDir = vol.path;
                        mSDcardDir = vol.internalPath;
                        Log.d(TAG,"hjc getSDcardDir()--mSDcardDir-->" + mSDcardDir); 
                    }
                }
            }
        }
		
		if (null != mSDcardDir) {
            /*int end = mSDcardDir.lastIndexOf('/');
            if (end > 0)    // case mSDcardDir = /xxx/xxx
                return mSDcardDir.substring(0, end);
            else            // case mSDcardDir = /xxx*/
                return mSDcardDir;
			//return mSDcardDir;
        } else {
            return null;
        }
 
    }
    
    /**
     * 获取所有SD卡路径
     * @param storageManager
     * @return
     */
    public static List<String> getSdCardPaths(StorageManager storageManager){
    	List<String> sdPaths = new ArrayList<String>();
        final List<VolumeInfo> volumes = storageManager.getVolumes();
        //Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo vol : volumes) {
            if (vol.getType() == VolumeInfo.TYPE_PUBLIC) {
                DiskInfo disk = vol.getDisk();
                if (disk != null) {
                    if (disk.isSd()) {
                        //sdPaths.add(vol.path);
                        sdPaths.add(vol.internalPath);
                    }
                }
            }
        }
		
		return sdPaths;
    
    }
    
    
    /**
     * 获取所有ＵＳＢ路径
     * @param storageManager
     * @return
     */
    public static List<String> getUsbPaths(StorageManager storageManager){

    	List<String> usbPaths = new ArrayList<String>();
        final List<VolumeInfo> volumes = storageManager.getVolumes();
        //Collections.sort(volumes, VolumeInfo.getDescriptionComparator());
        for (VolumeInfo vol : volumes) {
            if (vol.getType() == VolumeInfo.TYPE_PUBLIC) {
                DiskInfo disk = vol.getDisk();
                if (disk != null) {
                    if (disk.isUsb()) {
                        // TODO: 2020/5/6 本地测试使用path,正式环境改为internalPath
                    	//usbPaths.add(vol.path);
                    	usbPaths.add(vol.internalPath);
                    }
                }
            }
        }
		return usbPaths;
    }
}


 

