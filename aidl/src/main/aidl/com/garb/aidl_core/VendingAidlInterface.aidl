// VendingAidlInterface.aidl
package com.garb.aidl_core;

import com.garb.aidl_core.CallbackAidlInterface;
import com.garb.aidl_core.VendingDispatchItem;

interface VendingAidlInterface {
    String temperature();
    String currentState(String data);
    void dispatch(in List<VendingDispatchItem> items, CallbackAidlInterface callback);
}