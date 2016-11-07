package com.example.administrator.mpcharttest1;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.NtlmTransport;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　 ████━████     ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　 　 ┗━━━┓
 * 　　　　　　　　　┃ 神兽保佑　　 ┣┓
 * 　　　　　　　　　┃ 代码无BUG   ┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * Created by dutingjue on 2016/11/3.
 */
public class WebUtils {
    //WebService命名空间
    static final String SERVICE_NS = "http://localhost:80/WebserviceTest";

    //WebService提供服务的URL
    static final String SERVICE_URL = "http://192.168.30.65/WebserviceTest";
    //调用方法
    static final String methodName = "FailData";
    //调用的soapaction
    static final String username = "vmm";
    static final String password = "Password1";
    static final String domain = "domain";
    static final String workstation = "realm";

    public static String[] getPLATOData2() {
        NtlmTransport ntlmTransport = new NtlmTransport(SERVICE_URL);
        ntlmTransport.setCredentials(username, password, "", "");
        ntlmTransport.debug = true;
        //使用SOAP1.2协议创建Envelop对象
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        //设置与.Net提供的Web Service保持较好的兼容性
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        //实例化SoapObject对象
        SoapObject soapObject = new SoapObject(SERVICE_NS, methodName);
        envelope.bodyOut = soapObject;
        try {
            ntlmTransport.call(null, envelope);
            SoapObject response = (SoapObject) envelope.bodyIn;
            String dataset = response.getProperty(0).toString();
            String[] data1 = dataset.split("row1=anyType");
            return data1;
        } catch (IOException e) {
            e.printStackTrace();

        } catch (XmlPullParserException e) {
            e.printStackTrace();
            Log.i("false", "parser");

        }
        return null;
    }
}
