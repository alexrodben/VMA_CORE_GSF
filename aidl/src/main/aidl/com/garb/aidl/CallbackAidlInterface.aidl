// CallbackAidlInterface.aidl
package com.garb.aidl;

interface CallbackAidlInterface {
    void onProgress(int percentage, String message);
    void onWarning(String warning);
    void onComplete(String task);
    void onReceive(String data);
    void onError(String error);
}