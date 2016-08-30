package com.unicodelabs.jusibe.core.example;



import com.unicodelabs.jusibe.core.Jusibe;
import com.unicodelabs.jusibe.core.exceptions.IsNullException;
import com.unicodelabs.jusibe.core.utils.JusibeResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static final String PUBLIC_KEY = "09de5bec3937d2d032d8a75b41f9081b";
    public static final String ACCESS_TOKEN = "68501b2b40767fc51e4f936d7db40fc0";
  
    public static void main( String[] args ){
        try {
            final Jusibe client = new Jusibe(PUBLIC_KEY, ACCESS_TOKEN);
            final Map<String, String> smsParams = new HashMap<String, String>();
            
            smsParams.put("to", "08091167643"); // Replace with a valid phone number
            smsParams.put("from", "iamraphson"); // Replace with a valid phone number in your account
            smsParams.put("message", "Welcome to Jusibe JAVA lib");
            
            JusibeResponse smsResponse = client.sendSMS(smsParams);
            System.out.println(smsResponse.toString());
            
            JSONObject smsResultObject = 
                    (JSONObject)new JSONParser().parse(smsResponse.getResponseMessage());
            if(smsResponse.getResponseCode() == 200){
                System.out.println("your SMS Message ID is " + smsResultObject.get("message_id"));
                System.out.println("your SMS Status is " + smsResultObject.get("status"));
                System.out.println("SMS credit used is " + smsResultObject.get("sms_credits_used"));
                System.out.println("your request speed is " + smsResultObject.get("request_speed"));
            } else {
                System.out.println(smsResultObject.get("error"));
            }
            
            
            JusibeResponse balResponse = client.checkAvailableCredits();
            System.out.println(balResponse.toString());
            JSONObject balResultObject = 
                        (JSONObject)new JSONParser().parse(balResponse.getResponseMessage());
            if(balResponse.getResponseCode() == 200){
                System.out.println("your SMS balance is " + balResultObject.get("sms_credits"));
                System.out.println("your request speed is " + balResultObject.get("request_speed"));
            } else {
                System.out.println(balResultObject.get("error"));
            }
            
            
            JusibeResponse deliveryResponse = 
                    client.checkDeliveryStatus("w719zxz58q");
            System.out.println(deliveryResponse.toString());
            JSONObject deliveryResultObject = 
                        (JSONObject)new JSONParser().parse(deliveryResponse.getResponseMessage());
            if(deliveryResponse.getResponseCode() == 200){
                System.out.println("your SMS Status is " + deliveryResultObject.get("status"));
                System.out.println("your SMS Message ID is " + deliveryResultObject.get("message_id"));
                System.out.println("your SMS Sent Date is " + deliveryResultObject.get("date_sent"));
                System.out.println("your SMS Delivered Date is " 
                        + deliveryResultObject.get("date_delivered"));
                System.out.println("your request speed is " + deliveryResultObject.get("request_speed"));
            } else {
                System.out.println(deliveryResultObject.get("error"));
            }
            
            
        } catch (IsNullException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
