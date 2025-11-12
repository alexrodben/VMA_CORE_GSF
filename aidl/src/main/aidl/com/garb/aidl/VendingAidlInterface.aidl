// VendingAidlInterface.aidl
package com.garb.aidl;

import com.garb.aidl.CallbackAidlInterface;
import com.garb.aidl.VendingDispatchItem;

interface VendingAidlInterface {
    String temperature();
    String currentState(String data);
    void dispatch(in List<VendingDispatchItem> items, CallbackAidlInterface callback);
}