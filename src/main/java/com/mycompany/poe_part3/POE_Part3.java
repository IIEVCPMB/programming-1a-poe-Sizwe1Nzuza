/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.poe_part3;

import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author 4nzuz
 */

class Message {
   static Random rand = new Random();
static Map<String, String> messageMap = new HashMap<>();
static int messageNumber = 0;
static int TotalMessagesSent= 0;

public static String generateMessageID() {
String id;
do {
    id = String.valueOf(1000000000L + (Math.abs(rand.nextLong()) % 9000000000L));
}while (messageMap.containsKey(id)) ;
return id;

}
public static String generateMessageHash(String id, String message) {
String Firsttwonumbers = id.substring(0, 2);
int currentMessagenumber = messageNumber++ ;

String[] words = message.trim().split("\\s+") ;
String firstword = words.length >0 ? words[0] : "MSG" ;
String lastword = words.length > 1 ? words[words.length - 1] : firstword;
String hash = Firsttwonumbers + ":" + currentMessagenumber + ":" + firstword.toUpperCase() + lastword.toLowerCase();

return hash;
}

public static void storeMessageASJson(String id, String recipient, String message, String hash) {
JSONObject msgObject = new JSONObject();
msgObject.put("message_id", id);
msgObject.put("recipient", recipient);
msgObject.put("message", message);
msgObject.put("hash", hash);

JSONArray messagesArray = new JSONArray();
File jsonFile = new File("messages.json");

if (jsonFile.exists()){
try (FileReader reader = new FileReader(jsonFile)) {
messagesArray = (JSONArray) new org.json.simple.parser.JSONParser().parse(reader);
} catch (Exception e) {
e.printStackTrace();

}
}
messagesArray.add(msgObject) ;

try (FileWriter file = new FileWriter("messges.json")) {
file.write(messagesArray.toJSONString());

}catch (IOException e) {
e.printStackTrace();
}

}



    public static boolean checkMessageID (String number) {    //Boolean is declared *BEFORE* main(String[], will be pulled later
       
     
    return number.length() ==10 ; 
      }
     public static boolean checkRecipientCell (String number) {    //Boolean is declared *BEFORE* main(String[], will be pulled later
      
     return number != null && number.matches("\\+27\\d{9}"); 
     
     }

     
     
    //put in the class then call the whole class
     public static String createMessageHash(String message) {
     Map< String, String> Messagesender = new HashMap<>() ;
     
     
      String fileName = "Test.txt" ;
    String input = JOptionPane.showInputDialog("Enter text to write to 'text.txt' (text 'exit' to finish):");
        
        try (FileWriter writer = new FileWriter(fileName)) { //Inittialise file writer
        while (true) { //Infinity loop, will keep on asking you what you want to enter untul you press exit
        
        
        if (input.equalsIgnoreCase("exit")) { //Make sure to type 'exit' after the code you want --> enter --> then exit again
        break ; //infin=fity loop will break
        }
        writer.write(input + System.lineSeparator()); //Checks all spaces, makes sure it all shows on text file
        }
            JOptionPane.showMessageDialog(null,"Text was successfully written to 'text.txt"); //Will print this if your text has actually printed on text located on FileExplorer
            
        }catch (IOException e){ //to assiats in case any errors, tells you what is wrong (handls exceptions)
            
            JOptionPane.showMessageDialog(null,"An Error occured while writing to the file");
            e.printStackTrace() ; //Traces all the characters written
        }
        return null;
        

}
public static void incrementMessageCount() {
TotalMessagesSent++;

}
public static int getTotalMessagessent() {
return TotalMessagesSent ;
}

public static void displaytotalmessages(){
    JOptionPane.showMessageDialog(null, "Total Messages sent: "+ TotalMessagesSent, "\nMessage Statistics: ", JOptionPane.INFORMATION_MESSAGE);
}

    private void assertEquals(int i, int size) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void assertEquals(String test_Message, String message) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

public static class MessageData {
String id ;
String recipient ;
String message ;
String hash ;
int length ;


public MessageData(String id, String recipient, String message, String hash) {
this.id = id ;
this.recipient = recipient ;
this.message = message ;
this.hash = hash ;
this.length = message.length();
}

}
public static List <MessageData> sentMessages = new ArrayList<>() ;
public static List <MessageData> disregardedMessages = new ArrayList<>() ;
public static List <MessageData> StoredMessages = new ArrayList<>();
public static List <String> messageHashes = new ArrayList<>() ;
public static List <String> messageIDs = new ArrayList<>();



public static void displaySendersandRecipients() {
if (sentMessages.isEmpty()) {
JOptionPane.showMessageDialog(null, "No messages have been sent yet");
return;
}
StringBuilder sb = new StringBuilder(" All Sent Messages:\n\n") ;
for (MessageData msg : sentMessages) {
sb.append("Sender: you\n");
sb.append("Recipient: ").append(msg.recipient).append("\n");
sb.append("Message: ").append(msg.message).append("\n\n") ;
}
JOptionPane.showMessageDialog(null, sb.toString());
}

public static void Displaylongestmessage() {
MessageData longest = null ;
for (MessageData msg : sentMessages) {
    if (longest == null || msg.message.length() > longest.message.length() ) {
    longest = msg ;
    }
}
if (longest != null) {
JOptionPane.showMessageDialog(null, " Longest Message: \nTo: " + longest.recipient + "\nMessage: " + longest.message);
}

}
public static void SearchMessagebyID(String id) {
    String SearchID = JOptionPane.showInputDialog("Enter the Message ID to search") ;
for (MessageData msg : sentMessages) {
if (msg.id.equals(SearchID)) {
JOptionPane.showMessageDialog(null,"Message found :\nRecipient: " + msg.recipient + "\nMessage: " + msg.message);
return ;
}
}
JOptionPane.showMessageDialog(null,"No Message Found with that ID");
}



public static void searchMessagesbyRecipient(String recipient) {
recipient = JOptionPane.showInputDialog("Enter the recipient number to search: ");
boolean found = false ;

StringBuilder sb = new StringBuilder("Messages sent to "+ recipient + ":\n\n");

for (MessageData data : sentMessages) {
if (data.recipient.equals(recipient)) {
found = true ;
sb.append("- ").append(data.message).append(" (ID: ").append(data.id).append(")\n");
}
}
if (found) {
JOptionPane.showMessageDialog(null, sb.toString());
} else {
JOptionPane.showMessageDialog(null,"No Messages found for that Recipient.");
}
}


public static void DeleteMessagebyHash(String hash) {
Iterator<MessageData> it = sentMessages.iterator() ;
while (it.hasNext()) {
MessageData msg = it.next() ;
if (msg.hash.equals(hash)) {
int confirm = JOptionPane.showConfirmDialog(null, "Delete this message?\n" + msg.message, "Confirm Delete", JOptionPane.YES_NO_OPTION) ;
if (confirm == JOptionPane.YES_OPTION) {
it.remove() ;
JOptionPane.showMessageDialog(null, "Message Deleted Successfully");
}
return ;
}
}
JOptionPane.showMessageDialog(null," Hash Not Found");
}

public static void displayFullReport() {
StringBuilder sb = new StringBuilder(" Full Message Report: \n\n") ;
for (MessageData msg : sentMessages) {
   sb.append("ID: ").append(msg.id).append("\nRecipient: ").append(msg.recipient).append("\nMessage: ").append(msg.message).append("\nHash: ").append(msg.hash).append("\n-------------------\n") ;
              
}
JOptionPane.showMessageDialog(null,sb.toString());
}
public void testMessageArraycontainsexpectedData() {
Message.sentMessages.clear();
MessageData data = new MessageData("123", "+27123456789", "Test Message", "12:1:TESTmessage" ) ;
Message.sentMessages.add(data) ;

assertEquals(1, Message.sentMessages.size()) ;
assertEquals("Test Message", Message.sentMessages.get(0).message) ;
}

public static void deleteMessgaebyHash() {
String inputHash = JOptionPane.showInputDialog("Enter the message Hash to delete: ");

for (MessageData data : sentMessages) {
if (data.hash.equals(inputHash)) {
int confirm = JOptionPane.showConfirmDialog(null,"Found Message:\n" + "To: " + data.recipient + "\nMSG: "+ data.message + "\nDelete ?", "Confirm:", JOptionPane.YES_NO_OPTION);
 if (confirm == JOptionPane.YES_OPTION) {
 sentMessages.remove(data );
 JOptionPane.showMessageDialog(null, "Message Deleted");
 
 }else {
 JOptionPane.showMessageDialog(null, "Deletion Cancelled");
 }
return ;
}
}
JOptionPane.showMessageDialog(null,"No Message found with that Hash");
}
public static void SearchMessagesbyRecipient() {
String Recipient = JOptionPane.showInputDialog("Enter Recipient Number to Search: ") ;

StringBuilder results = new StringBuilder("Messages sent to " + Recipient + ":\n\n") ;
boolean found = false ;

for (MessageData data : sentMessages) {
if (data.recipient.equals(Recipient)) {
found = true ;
results.append("Id: ").append(data.id).append("\nHash: ").append(data.hash).append("\nMessage: ").append(data.message).append("\n\n") ;
}
}
if (found) {
JOptionPane.showMessageDialog(null,results.toString());
} else {
JOptionPane.showMessageDialog(null, "No Messages found from that Recipient");
}
}





}

public class POE_Part3 {
    
   private static String SavedPassword ;
   
    public static boolean checkPasswordComplexity(String Password) {
    
        if (Password.length() < 8) {
            return false ;
        }
        String Uppercase = "(?=.*[A-Z])" ;
        String Digit = "(?=.*\\d)" ;
        String SpecialChar = "(?=.*[^a-zA-Z0-9])" ;
        String regrex = "^" + Uppercase + Digit + SpecialChar + ".{8,}$" ;
        return Password.matches(regrex) ;
    } 
    
    public static boolean checkCellPhoneNumber (String number) {
        return number.matches("\\+27\\d{9}") && number.length() == 12; 
    }
   
    public static boolean checkUserName (String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static void main(String[] args) throws InterruptedException {
         
        int choice = JOptionPane.showConfirmDialog(null, "Would you like to create an Account?" + JOptionPane.YES_NO_CANCEL_OPTION);
        
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, "Creating an Account, please wait...");
            Thread.sleep(2000);
        
            String Username = JOptionPane.showInputDialog("Creating an Account...\nCreate an Account username with a max of 5 characters, use an '_' ");
            String Password = JOptionPane.showInputDialog("Creating an Account....\nCreate an Account password with minimum of 8 characters(i.e: 8 or more), A Capital letter, A Number and A Special Character: ");
            String phone = JOptionPane.showInputDialog("Creating an Account...\nEnter a valid RSA Phone Number. EG: +270000000000");
        
            if (checkCellPhoneNumber(phone)) {
                JOptionPane.showMessageDialog(null, "Creating an Account...\nCell Phone Number successfully added");
            } else {
                JOptionPane.showMessageDialog(null, "Creating an Account(FAILED)...\nCell Phone number incorrectly formatted or does not contain international code.");
            }

            if (!checkUserName(Username)) {
                JOptionPane.showMessageDialog(null, "Creating an Account(FAILED)...\nUsername is not correctly formatted,\n please ensure that your username contains: \n an underscore and is no more than 5 Characters in length.");
            } else if (checkUserName(Username)) {
                JOptionPane.showMessageDialog(null, "Creating an Account...\nUsername successfully captured");
            }

            if (!checkPasswordComplexity(Password)) {
                JOptionPane.showMessageDialog(null, "Creating an Account(FAILED)...\nPassword is not correctly formatted; \nPlease ensure that the password contains: \nAt least eight(8) characters, a capital letter, a number, and a special character.");
            } else if (checkPasswordComplexity(Password)) {
                JOptionPane.showMessageDialog(null, "Creating an Account...\nPassword Successfully Captured");
            }

            if ((!checkCellPhoneNumber(phone)) || (!checkPasswordComplexity(Password)) || (!checkUserName(Username))) {
                JOptionPane.showMessageDialog(null, "Account Creation Failed");
            } else if ((checkCellPhoneNumber(phone)) && (checkPasswordComplexity(Password)) && (checkUserName(Username))) {
                JOptionPane.showMessageDialog(null, "Account Created!");
            }

            String password = Password;
            SavedPassword = password;

            if ((checkCellPhoneNumber(phone)) && (checkPasswordComplexity(password)) && checkUserName(Username)) {
                int Choice = JOptionPane.showConfirmDialog(null, "Would you like to Log in?" + JOptionPane.YES_NO_CANCEL_OPTION);
                
                if (Choice == JOptionPane.YES_OPTION) {
                    boolean run = true ;
                    int count = 0;
                    
                    while (run) {
                    String userLogin = JOptionPane.showInputDialog("Logging in\nEnter Username to login:");
                    String passLogin = JOptionPane.showInputDialog("Logging in\nEnter Paswword: ");
                    
                
                    if (!(Username.equals(userLogin)) || !(passLogin.equals(password))) {
                        JOptionPane.showMessageDialog(null, "Login Unsuccessful.\nUsername or Password Login Incorrect, Please try again");
                    count++ ;
                    if (count == 3) {
                      run = false ; 
                    } 
                    } else if (Username.equals(userLogin) && passLogin.equals(password)) {
                        JOptionPane.showMessageDialog(null, "Login Successful\nWelcome back, " + Username + "! It is great to see you again!");
                        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!!! \uD83C\uDF89 ");
                       
                    run = false ;
                    }
                   
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "Okay, Goodbye!");
        }
    



    

Random rand = new Random() ;
Map<String, String> messageMap = new HashMap<>() ;

boolean running = true ; //while this (running) is true, the code will continue to run
while (running) { //start the while loop and allow the user into the ChatApp
JOptionPane.showMessageDialog(null, "\uD83C\uDF89 Welcome to QuickChat!!! \uD83C\uDF89 ");
     String Userinput = JOptionPane.showInputDialog("\nWould you like to \n1) Send Messages, \n2) Show recently sent messages \n3) Search Message by ID \n4) Search Messages By Recipient \n5) Delete Message by Hash \n6) Display Full Report \n7) Quite?"); //User is prompted to enter numbers to preform specific actions (1-send messages, 2-view messages, 3- quit
            
          if (Userinput == null) { 
            JOptionPane.showMessageDialog(null, "!!Invalid!! Input", "Please enter: 1, 2, or 3", JOptionPane.WARNING_MESSAGE);
            Thread.sleep(2000) ;
           //if the user doesn't input anything, it will tell them that their input is invalid and that they must insert 1, 2. or 3

} else if (Userinput.equals("1")) { //if the user inserts 1, the User can insert messages
JOptionPane.showMessageDialog(null,"Welcome to the QuickChat Chat menu!! \nThis is where you can send messages!");

int Messagecount = Integer.parseInt(
JOptionPane.showInputDialog("How many messages do you want to send?", JOptionPane.QUESTION_MESSAGE)
); //User inserts the amount of messages they want to send, stored in 'Messagecount'
String [] messages = new String [Messagecount];
int [] numbers = new int [Messagecount] ;
int messageCounter = 1; //



 for (int i = 0; i < Messagecount ; i ++) {
//Each new message is labelled starting at 1 (Eg: Message 1, Message 2 etc.)
      String number = null ;
      boolean Validnumber = false ;   
     

      
while (!Validnumber) {
String Inputnumber = JOptionPane.showInputDialog("Enter the number of the recipient \ninclude +27 and 9 digits and (+27xxxxxxxxxxxx");

if (Inputnumber == null) {
  int Exitchoice = JOptionPane.showConfirmDialog(null, "DO you want to exit QuickChat?", "\n Confirm: ", JOptionPane.YES_NO_OPTION);
  

if (Exitchoice ==JOptionPane.YES_OPTION){
    JOptionPane.showMessageDialog(null, "Exiting QuickChat...");
    Thread.sleep(200) ;
    System.exit(0) ;
}else {
 continue ;
}
}

if (Message.checkRecipientCell(Inputnumber)) {
number = Inputnumber ;
Validnumber = true ;

} else {
JOptionPane.showMessageDialog(null,"\uD83D\uDC4E Invalid number Format!\n->Number must start with '+27'", " \n->Followed by 9 numbers \nEg:+27123456789 ", JOptionPane.ERROR_MESSAGE);

}
}
 


String message = null;
boolean Validmessage = false ;

while (!Validmessage) {
    
String Inputmessage = JOptionPane.showInputDialog("Enter Message (max: 250 characters) " +(i +1) + " for number: "+ number +"\nClick 'OK' or hit 'ENTER' when you are finished typing your message" + " :");

if (Inputmessage == null) {
int Exit = JOptionPane.showConfirmDialog(null, "Do you want ", " to exit QuickChat?", JOptionPane.YES_NO_OPTION) ;


if (Exit == JOptionPane.YES_OPTION) {
    Thread.sleep(200);
JOptionPane.showMessageDialog(null, "Exiting QuickChat...");
System.exit(0) ;
}

} else if (Inputmessage.length() > 250) {
JOptionPane.showMessageDialog(null, "Too many characters\n", " Please enter a message that is less than 250 character \u274C", JOptionPane.ERROR_MESSAGE);

}else {
    message = Inputmessage ;
    Validmessage = true ;
}

}

 //Will give each message a unique identification code
String messageID = Message.generateMessageID() ;
String hash = Message.generateMessageHash(messageID, message) ;
Message.MessageData data = new Message.MessageData (messageID, number, message, hash) ;
Message.sentMessages.add(data) ;
Message.messageHashes.add(hash) ;
Message.messageIDs.add(messageID) ;

JOptionPane.showMessageDialog(null, "Recipient: "+ number+"\nMessage number: "+ messageCounter + "\nSent with ID: "+ messageID +"\nmessage is: "+ message + "\nMessage Hash: " + hash); //Will print the number of the message (Message 1, Message 2 etc.) as well as the identification code for that message.

Message.sentMessages.add(data) ;
Message.messageIDs.add(messageID) ;
Message.messageHashes.add(hash) ;
Message.disregardedMessages.add(data) ;
Message.StoredMessages.add(data) ;

messageCounter++; //Message counter will continue to go up from 1 until the user stops putting in messages






String fileName = "Test.txt" ; //Message will be saved here, File will be created or overwritten in the project folder


try (FileWriter writer = new FileWriter(fileName, true)) { //created a file writer to write onto the Test.text -- 'true' is for append mode (New data is added to the end of the existing file content)
for (Map.Entry<String, String> entry : messageMap.entrySet()) {
writer.write("Message ID: " + entry.getKey() + "\n"); //entry.getKey() is the message ID
writer.write("Recipient: "+ number + "\n"); 
writer.write("Message: " + entry.getValue() + "\n"); //entry.getValue() is the message that the user sends
writer.write("Hash: " + hash + "\n");
writer.write("----------\n"); //Will separate the user's entires

}
JOptionPane.showMessageDialog(null,"Message with MessageID ready to send "); //confirms whether all messages have been saved
} catch (IOException e) { //will catch any errors
JOptionPane.showMessageDialog(null, "An error occured when sending messages...","..",JOptionPane.ERROR_MESSAGE); //if not all messages are saved, then the user will be notofied
e.printStackTrace();
}

int option = JOptionPane.showOptionDialog(null, "Message saved \u2705 , what do you want to do?", "Message Options", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String []{"Send message", "Disregard Message", "Store Message to send later"}, "Send Message");

switch (option) {
case 0: //User chose to send message
JOptionPane.showMessageDialog(null, "Message sent with ID: "+ messageID);
break;

case 1: //User chose to Disregard the message
Message.sentMessages.remove(data) ;
Message.disregardedMessages.add(data) ;
messageMap.remove(messageID);
JOptionPane.showMessageDialog(null, "Message dicarded \uD83D\uDDD1");
break;

case 2: //User chose to store message
Message.StoredMessages.add(data) ;
Message.storeMessageASJson(hash, messageID, messageID, hash);
JOptionPane.showMessageDialog(null, "Message now stored to JSON file \u2705");
break ;

default: //neither 0, 1 or 2
JOptionPane.showMessageDialog(null, "Invalid Input. Please enter 0, 1 or 2");


}

Message.incrementMessageCount();
JOptionPane.showMessageDialog(null, "TOTAL Messages sent "+ Message.getTotalMessagessent() + "---");

 }
 
 

 Message.displaytotalmessages() ;
JOptionPane.showMessageDialog(null,"Total Messages Reached");
JOptionPane.showMessageDialog(null,"Retruning to main Menu...");
Thread.sleep(200) ;
    try {
        Thread.sleep(200);
    } catch (InterruptedException ex) {
        Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
    }
      

       

 
}

else if (Userinput.equals("2")) { //if the user selects 2 in the main menu, this block will print
Message.displaySendersandRecipients();
Message.displayFullReport();
Message.Displaylongestmessage();



}
else if (Userinput.equals("3")) {
Message.SearchMessagebyID(Userinput);


}

else if (Userinput.equals("4")) {
Message.SearchMessagesbyRecipient();


}


else if (Userinput.equals("5")) {
Message.deleteMessgaebyHash();


}
else if (Userinput.equals("6")) {
Message.displayFullReport();
Message.Displaylongestmessage();

}


else if (Userinput.equals("7")) { //if the user wishes to quit the app, this block will print
JOptionPane.showMessageDialog(null,"Exiting QuickChat...","..", JOptionPane.WARNING_MESSAGE);
int exiting= JOptionPane.showConfirmDialog(null,"Are you sure you would like to quite QuickChat?","Exiting QuickChat...", JOptionPane.YES_NO_OPTION);

if (exiting == JOptionPane.YES_OPTION) { //if user wants to exit (and selects yes), the app will close
JOptionPane.showMessageDialog(null, "Exiting QuickChat...");
running = false ;
System.exit(0);


} else { //If user reconsiders and no longer wants to exit, they will retrun to the main menu
    JOptionPane.showMessageDialog(null, "Returning to QuickChat...");
    try {
        Thread.sleep(200);
    } catch (InterruptedException ex) {
        Logger.getLogger(Message.class.getName()).log(Level.SEVERE, null, ex);
    }
}

} else{ //If user enters anything other than 1, 2, or 3
    JOptionPane.showMessageDialog(null, "Invalid Input!!","\nPlease enter: 1, 2, 3, 4 or 5.", JOptionPane.ERROR_MESSAGE);
    
}
}


        }
}
    }
}







