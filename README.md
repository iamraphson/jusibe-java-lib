### Jusibe-java-lib

 > Jusibe Library for JAVA☕️. This is based on the Official API Documentation provided by [Jusibe](https://jusibe.com/docs/) a Nigerian SMS Service.


### Registration

First, you will need to first create an account at [Jusibe.com](https://jusibe.com/signup/) and obtain your Public key and Access Token.

Once you have created an account, you can access your Public key and Access Token from the [API Keys](https://jusibe.com/cp/?section=api-keys).

# Installation

## Prerequisites

- Java version Oracle JDK 7, 8 or OpenJDK 7

### Maven
Include the following in your `pom.xml` for Maven:

```xml
<dependency>
    <groupId>com.unicodelabs</groupId>
    <artifactId>jusibe-java-lib</artifactId>
    <version>1.0.0</version>
</dependency>
```

###Jar-File
You can also download the jar file from the latest release on the [releases page](https://github.com/iamraphson/jusibe-java-lib/releases).

##Usage
A Jusibe class provides three public methods for accessing the API. Instantianting the class is as given below:


```java
final Jusibe client = new Jusibe();
```

Its constructor takes two string parameters:

```java
public final String PUBLIC_KEY = "[Enter Public Key Here]";
public final String ACCESS_TOKEN = "[Enter Access Token]";

final Jusibe client = new Jusibe(publicKey, accessToken);
```

### Jusibe Class Methods
Its methods are:

#### Send SMS
This lets you make a request to the Jusibe API, to send an SMS. It takes a `java.util.Map<String, String>` object as a parameter and returns a `com.iamraphson.jusibe.core.utils.JusibeResponse` object.

##### Usage
```java
try {
    final Jusibe client = new Jusibe("{PUBLIC_KEY}", "{ACCESS_TOKEN}");
    final Map<String, String> smsParams = new HashMap<String, String>();

    smsParams.put("to", "XXXXXXXXXX"); // Replace with a valid phone number
    smsParams.put("from", "Jusibe Joe"); // Replace with a valid Sender
    smsParams.put("message", "Welcome to Jusibe JAVA lib");

    JusibeResponse smsResponse = client.sendSMS(smsParams);
    System.out.println(smsResponse.toString());

    JSONObject smsResultObject = (JSONObject)new JSONParser().parse(smsResponse.getResponseMessage());
    if(smsResponse.getResponseCode() == 200){
        System.out.println("your SMS Message ID is " + smsResultObject.get("message_id"));
        System.out.println("your SMS Status is " + smsResultObject.get("status"));
        System.out.println("SMS credit used is " + smsResultObject.get("sms_credits_used"));
        System.out.println("your request speed is " + smsResultObject.get("request_speed"));
    } else {
        System.out.println(smsResultObject.get("error"));
    }
} catch (IsNullException ex) {
    System.out.println(ex.getMessage());
} catch (IOException ex) {
    System.out.println(ex.getMessage());
} catch (ParseException ex) {
    System.out.println(ex.getMessage());
}
```
#### Check SMS Delivery Status
This gives you information on the delivery status of previous sent messages. It takes a single paramter: `string messageID` and returns a `com.iamraphson.jusibe.core.utils.JusibeResponse` object

##### Usage
```java
try {
    final Jusibe client = new Jusibe("{PUBLIC_KEY}", "{ACCESS_TOKEN}");
    JusibeResponse deliveryResponse = client.checkDeliveryStatus("w719zxz58q");
    System.out.println(deliveryResponse.toString());
    JSONObject deliveryResultObject = (JSONObject)new JSONParser().parse(deliveryResponse.getResponseMessage());
    if(deliveryResponse.getResponseCode() == 200){
        System.out.println("your SMS Status is " + deliveryResultObject.get("status"));
        System.out.println("your SMS Message ID is " + deliveryResultObject.get("message_id"));
        System.out.println("your SMS Sent Date is " + deliveryResultObject.get("date_sent"));
        System.out.println("your SMS Delivered Date is " + deliveryResultObject.get("date_delivered"));
        System.out.println("your request speed is " + deliveryResultObject.get("request_speed"));
    } else {
        System.out.println(deliveryResultObject.get("error"));
    }
} catch (IsNullException ex) {
    System.out.println(ex.getMessage());
} catch (IOException ex) {
    System.out.println(ex.getMessage());
} catch (ParseException ex) {
    System.out.println(ex.getMessage());
}
```

#### Get Credits
This makes an authentication request to the Jusibe API, and returns a `com.iamraphson.jusibe.core.utils.JusibeResponse` as Response.

##### Usage
```java
try{
    final Jusibe client = new Jusibe("{PUBLIC_KEY}", "{ACCESS_TOKEN}");
    JusibeResponse balResponse = client.checkAvailableCredits();
    System.out.println(balResponse.toString());
    JSONObject balResultObject = (JSONObject)new JSONParser().parse(balResponse.getResponseMessage());
    if(balResponse.getResponseCode() == 200){
        System.out.println("your SMS balance is " + balResultObject.get("sms_credits"));
        System.out.println("your request speed is " + balResultObject.get("request_speed"));
    } else {
        System.out.println(balResultObject.get("error"));
    }
} catch (IsNullException ex) {
    System.out.println(ex.getMessage());
} catch (IOException ex) {
    System.out.println(ex.getMessage());
} catch (ParseException ex) {
    System.out.println(ex.getMessage());
}
```

### Jusibe Models
You may have seen references to Classes such as `com.iamraphson.jusibe.core.utils.JusibeResponse` in above sections of this documents.
- `JusibeResponse` class
      - `string responseCode`
      - `string responseMessage`
      - `string getResponseCode()`
      - `setResponseCode(int responseCode)`
      - `string getResponseMessage()`
      - `setResponseMessage(String responseMessage)`

# Example
Here is an example

```java
package com.iamraphson.jusibe.core.example;

import com.iamraphson.jusibe.core.Jusibe;
import com.iamraphson.jusibe.core.exceptions.IsNullException;
import com.iamraphson.jusibe.core.utils.JusibeResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * @author Raphson
 */
public class App {
    public static final String PUBLIC_KEY = "XXXXXXXXXXXXXXXXXXXXXXXXXX";
    public static final String ACCESS_TOKEN = "XXXXXXXXXXXXXXXXXXXXXXXXXXX";

    public static void main( String[] args ){
        try {
            final Jusibe client = new Jusibe(PUBLIC_KEY, ACCESS_TOKEN);
            final Map<String, String> smsParams = new HashMap<String, String>();

            smsParams.put("to", "xxxxxxxxxxxx"); // Replace with a valid phone number
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
```
## Contributing

Please feel free to fork this package and contribute by submitting a pull request to enhance the functionalities.


## Security Vulnerabilities

If you discover a security vulnerability within Jusibe java library , please send an e-mail to Ayeni Olusegun at nsegun5@gmail.com. All security vulnerabilities will be promptly addressed.

## How can I thank you?

Why not star the github repo? I'd love the attention! Why not share the link for this repository on Twitter or HackerNews? Spread the word!

Don't forget to [follow me on twitter](https://twitter.com/iamraphson)!

Thanks!
Ayeni Olusegun.

## License

The MIT License (MIT). Please see [License File](LICENSE.md) for more information.
