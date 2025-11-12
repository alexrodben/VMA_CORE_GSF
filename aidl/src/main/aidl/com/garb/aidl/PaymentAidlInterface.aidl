// PaymentAidlInterface.aidl
package com.garb.aidl;

import com.garb.aidl.CallbackAidlInterface;
import com.garb.aidl.PaymentSuccess;

interface PaymentAidlInterface {
     void revesalString (String autorization, CallbackAidlInterface callback);
     void sale(double amount, CallbackAidlInterface callback);
     PaymentSuccess paymentResponse(String idTransaccion);
     String cancel( );
}