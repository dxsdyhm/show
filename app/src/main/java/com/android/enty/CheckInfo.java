package com.android.enty;

/**
 * @Author: dxs
 * @time: 2020/5/7
 * {
 *     "serialNO":"HCYRK3318123456851",
 *     "wifiMacAddr":"e0:76:d0:38:69:10",
 *     "macAddr":"e0:76:d0:38:69:11",
 *     "bluetoothAddr":"e0:76:d0:38:69:12"
 * }
 * @Email: duanxuesong12@126.com
 */
public class CheckInfo {
    private String serialNO="";
    private String wifiMacAddr="";
    private String macAddr="";
    private String bluetoothAddr="";

    public CheckInfo() {
    }

    public CheckInfo(String serialNO, String wifiMacAddr, String macAddr, String bluetoothAddr) {
        this.serialNO = serialNO;
        this.wifiMacAddr = wifiMacAddr;
        this.macAddr = macAddr;
        this.bluetoothAddr = bluetoothAddr;
    }

    public String getSerialNO() {
        return serialNO;
    }

    public void setSerialNO(String serialNO) {
        this.serialNO = serialNO;
    }

    public String getWifiMacAddr() {
        return wifiMacAddr;
    }

    public void setWifiMacAddr(String wifiMacAddr) {
        this.wifiMacAddr = wifiMacAddr;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

    public String getBluetoothAddr() {
        return bluetoothAddr;
    }

    public void setBluetoothAddr(String bluetoothAddr) {
        this.bluetoothAddr = bluetoothAddr;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "serialNO='" + serialNO + '\'' +
                ", wifiMacAddr='" + wifiMacAddr + '\'' +
                ", macAddr='" + macAddr + '\'' +
                ", bluetoothAddr='" + bluetoothAddr + '\'' +
                '}';
    }
}
