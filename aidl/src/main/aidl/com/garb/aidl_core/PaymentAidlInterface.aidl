// PaymentAidlInterface.aidl
package com.garb.aidl_core;

import com.garb.aidl_core.CallbackAidlInterface;
import com.garb.aidl_core.PaymentSuccess;

interface PaymentAidlInterface {
     void revesalString (String autorization, CallbackAidlInterface callback);
     void sale(double amount, CallbackAidlInterface callback);
     PaymentSuccess paymentResponse(String idTransaccion);
     String cancel( );
}