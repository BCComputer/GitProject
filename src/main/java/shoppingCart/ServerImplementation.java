package shoppingCart;/* Name: Bishnu Pandey
Date: 04/18/2020
Course/Section: IT 106 004
Assignment: Programming Assignment 8

This assignment is intended to teach how to implement a object-orinted solution in
real world. This programm will create webserver or fileserver with the maximum capacity
of 200 servers. The solution will allow for a new server to be added and display a report
containing detail server information.

This object-oriented class allow the user to enter a server name, and choose a server type.
It also ask the installed operating system, and harddisk capacity (in TB). Specifically
for file server it will ask number of user and for web server web server software software
supported by the server.

Option menus are : [1] Add server, [2] Display All Server Information [3] Display Average
usage by Server Type, [4] Display Highest Usage server by Server Type and [5] Quit.


The requirements are: The maximum server capacity is 200. Operating system must be
Windows, Linux, or OS X. The level of usease should be on a scale of 0 to 100 and
no two servers on the network

   This is an implementation class.
   */

import javax.swing.*;

public class ServerImplementation {

   public static void main(String[] args) {
   // Array of Server.
      Server[] serverLists = new Server[Server.MAX_SERVER_CAPACITY];
   
      int choice = 0;
      do {
         choice = mainMenuChoice(serverLists);
      } while (choice != 5);
   
   }

   /*mainMenuChoice method switching between user input.
    * @return response to the main method.
    */

   public static int mainMenuChoice(Server[] serverLists) {
      int response;
      do {
         response = createMenu();
         switch (response) {
            case 1:
               if (Server.getNumOfServer() > Server.MAX_SERVER_CAPACITY) {
                  JOptionPane.showMessageDialog(null, "Server Capacity is Full");
               } else {
                  addServer(serverLists);
               }
               break;
         
            case 2:
               displayAllServerInfo(serverLists);
               break;
            case 3:
               displayAvgServerUsage(serverLists);
               break;
            case 4:
               displayMaxServerUsage(serverLists);
               break;
            case 5:
               quit();
               break;
            default:
               if (response != 5) {
                  JOptionPane.showMessageDialog(null, "Please select an option in the drop down menu");
               }
         }
      } while (response != 5);
      return response;
   }

   //createMenu method creates a menu to choose the list of to do list.
   //@retrun menuChoice.
   public static int createMenu() {
      final int MIN_OPTION = 1;
      final int MAX_OPTION = 5;
      int menuChoice;
      do {
         try {
            menuChoice = Integer.parseInt(JOptionPane.showInputDialog(
                   "Enter your selection:"
                           + "\n[1] Add Server"
                           + "\n[2] Display All Server Information"
                           + "\n[3] Display Average usage by Server Type"
                           + "\n[4] Display Highest Usage Server by Server Type"
                           + "\n[5] Quit"
               ));
         } catch (NumberFormatException e) {
            menuChoice = 0;
         }
         if (menuChoice < MIN_OPTION || menuChoice > MAX_OPTION) {
            JOptionPane.showMessageDialog(null, "Invalid choice. Please enter the number between 1 to 5 menu option.");
         }
      } while (menuChoice < MIN_OPTION || menuChoice > MAX_OPTION);
   
      return menuChoice;
   }

   //getServerChoice method create the file server and web server menu allow the user to choose.
   //@retrun menuChoice.
   public static int getServerChoice() {
      final int MIN_OPTION = 1;
      final int MAX_OPTION = 2;
      int menuChoice;
      do {
         try {
            menuChoice = Integer.parseInt(JOptionPane.showInputDialog(
                   "Please Select Servers:"
                           + "\n[1] File Server"
                           + "\n[2] Web Server"
               ));
         }
         catch (NumberFormatException e) {
            menuChoice = 0;
         }
         if (menuChoice < MIN_OPTION || menuChoice > MAX_OPTION) {
            JOptionPane.showMessageDialog(null, "Invalid choice. Please enter the number between 1 to 2 menu option.");
         }
      } while (menuChoice < MIN_OPTION || menuChoice > MAX_OPTION);
      return menuChoice;
   }

   //getHardDriveCapacity method get the hard disk capacity from the user. and return the integer value.
   public static int getHardDriveCapacity(){
      int hdCapacity = 0;
      do{
         try{
            hdCapacity = Integer.parseInt(JOptionPane.showInputDialog("Enter server HD capacity in TB:"));
            if(hdCapacity < 1){
               JOptionPane.showMessageDialog(null, "Number should be positive");
            }
         } catch(NumberFormatException e){
            hdCapacity = hdCapacity-1;
         }
      } while((hdCapacity < 1));
      return hdCapacity;
   }

   // serverSoftwareName method takes the user input of web server software installed in the computer and takes the maxmum four software. and return the array list.
   public static String[] serverSoftwareName() {
      boolean isValid = false;
      int num = 0;
      do {
         try {
            num = Integer.parseInt(JOptionPane.showInputDialog("How many server software do you have?"));
         }catch(NumberFormatException e) {
            num = num - 1;
         }
         if (num < 0 || num > 4) {
            JOptionPane.showMessageDialog(null,
                       "Invalid value. Please enter a value between 1 and 4");
         }
      } while (num < 0 || num > 4);
   
      String [] serverName = new String [num];
      int currentSize = 0;
      do {
         String software =   JOptionPane.showInputDialog("Enter the web server software" + (currentSize +1) +
                "(e.g. Apache, IIS, Apache Tomcat and Nginx), if you finish enter Done" );
         if(software.equals("")) {
            JOptionPane.showMessageDialog(null, "Name Can't left blank");
         } else {
            serverName[currentSize] = software;
            currentSize++;
         }
      } while(currentSize < serverName.length);
      return serverName;
   }

   /* addServer method creates a file server or web server object base on user choice and takes and gathers required input from user.
    * @return serverLists to main method.
    */

   public static void addServer(Server [] serverLists) {
      String name;
      int numServer = Server.getNumOfServer();
      if (numServer + 1 > Server.MAX_SERVER_CAPACITY) {
         JOptionPane.showMessageDialog(null, "Server is full, cannot create anymore");
      } else {
         do {
            name = JOptionPane.showInputDialog("Enter Server Name :");
            if ((name == null || name.equals(""))) {
               JOptionPane.showMessageDialog(null, "EnterEnter Valid Name");
            }
         } while (name == null || name.equals(""));
      
         int menuChoice = getServerChoice();
         String serverType;
         String operatingSys = getOperatingSystem();
         int hdCapacity = getHardDriveCapacity();
      
         if (menuChoice == 1) {
            serverType = "File Server";
            int numOfFileServerUser = getNoOfFileServerUser(FileServer.MIN_LEVEL_USE, FileServer.MAX_USER);
            serverLists[numServer] = new FileServer(name, serverType, hdCapacity, operatingSys, numOfFileServerUser);
            
         
         } else {
            serverType = "Web Server";
            String[] serverSoftwarePackage = serverSoftwareName();
            serverLists[numServer] = new WebServer(name, serverType, hdCapacity, operatingSys, serverSoftwarePackage);
         }
      }
   }



   // getNoOfFileServerUser method takes the user input of file server user and return the integer value to the add server method.
   public static int getNoOfFileServerUser(int minValue, int maxValue) {
      int numUser;
      do {
         try {
            numUser = Integer.parseInt(JOptionPane.showInputDialog("Enter the users between 0 to 5000:"));
         }
         catch(NumberFormatException e) {
            numUser = minValue - 1;
         }
         if (numUser < minValue || numUser > maxValue) {
            JOptionPane.showMessageDialog(null,
                   "Invalid value. Please enter a value between " + minValue + " and " + maxValue);
         }
      } while (numUser < minValue || numUser > maxValue);
      return numUser;
   }

   // getOperatingSystem method takes the user select input operating system [windows, Linux, or OS X"
   public static String getOperatingSystem(){
      String operatingSys;
      do{
         operatingSys = JOptionPane.showInputDialog("Select installed operating System: Windows, Linux, or OS X");
         if ((!operatingSys.equalsIgnoreCase("Windows")) && (!operatingSys.equalsIgnoreCase("Linux"))
            && (!operatingSys.equalsIgnoreCase("OS-X"))){
            JOptionPane.showMessageDialog(null, "Please enter server name  Windows, Linux, or OS X");
         }
      } while((!operatingSys.equalsIgnoreCase("Windows")) && (!operatingSys.equalsIgnoreCase("Linux"))
         && (!operatingSys.equalsIgnoreCase("OS X")));
      return operatingSys;
   }

 /*  displayAllServerInfo method  takes the array of Server object and print the server report
     including Name, Type, OS, Hard Drive (in TB), Usage, # of User Accounts if file server and Web Servers software if web server.
 */

   public static void displayAllServerInfo(Server[] serverLists){
      String output ="";
      int hdCapacity = 0;
      for (int i = 0; i<Server.getNumOfServer(); i++){
         output += serverLists[i].toString()+ " \n"  ;
      }
      for (int i = 0; i<Server.getNumOfServer(); i++){
         hdCapacity += serverLists[i].getHdCapacity();
      }
   
      JOptionPane.showMessageDialog(null, "Server Report__________________________________________\n\n" + output + "\n\n\n" +
             "Total Server : " + Server.getNumOfServer() + "\n" +
             "Harddisk Capacity : " + hdCapacity);
   }

     /*  displayAvgServerUsage method  takes the array of Server object and
     print the average file and web server uses report.
     */

   public static void displayAvgServerUsage(Server[] serverLists){
      double fileSeverTotal =0;
      double webServerTotal = 0;
      int fileServerCount = 0;
      int webServerCount = 0;
      double FileServerAvg = 0;
      double WebServerAvg = 0;
      for(int i = 0; i<Server.getNumOfServer(); i++){
         if (serverLists[i].getServerType().equals("File Server")){
            fileSeverTotal += serverLists[i].serverUsEase();
            fileServerCount++;
         } else{
            webServerTotal += serverLists[i].serverUsEase();
            webServerCount++;
         }
      }
      if (fileServerCount>0) {
         FileServerAvg = fileSeverTotal / fileServerCount;
      }
      if (webServerCount>0) {
         WebServerAvg = webServerTotal / webServerCount;
      }
      JOptionPane.showMessageDialog(null, "Average Usease Report____________________________\n\n" + "File Server Average Usage : " + String.format("%.2f",FileServerAvg)
                                                         + "\nWeb Server Average Usage : " +String.format("%.2f", WebServerAvg));
   }

       /*
       displayMaxServerUsage method  takes the array of Server object and
        print the maximum usage file and web server.
       */
   public static void displayMaxServerUsage(Server[] serverLists){
      String maxFileUseName = "";
      String maxWebUseName = "";
   
      double maxWebUse = 0.00;
      double  maxFileUse = 0.00;
   
      for (int i = 0; i < Server.getNumOfServer(); i++) {
         if (serverLists[i].getServerType().equals("File Server")) {
            maxFileUseName = serverLists[i].getServerName();
         
            maxFileUse = serverLists[0].serverUsEase();
            if (serverLists[i].serverUsEase() > maxFileUse) {
               maxFileUse = serverLists[i].serverUsEase();
               maxFileUseName = serverLists[i].getServerName();
            }
         }
      }
      for (int i = 0; i < Server.getNumOfServer(); i++) {
         if (serverLists[i].getServerType().equals("Web Server")) {
            maxWebUseName = serverLists[i].getServerName();
            maxWebUse = serverLists[0].serverUsEase();
            if (serverLists[i].serverUsEase() > maxWebUse) {
               maxWebUse = serverLists[i].serverUsEase();
               maxWebUseName = serverLists[i].getServerName();
            }
         }
         if(maxFileUseName.equals("")){
            maxFileUseName = "N/A";
         }
         if(maxWebUseName.equals("")){
            maxWebUseName = "N/A";
         }
      }
      JOptionPane.showMessageDialog(null, "Highest Usease Report____________________________\n\n" +
                                                                    "Highest Use File User : " + "\" "+ maxFileUseName + " \""+ " Useses : " + String.format("%.2f", maxFileUse) +
                                                                    "\nHighest Use Web User: " + "\" " + maxWebUseName + " \""+ " Useses : " + String.format("%.2f", maxWebUse));
   }

   // quit method allow the user exit the application.
   public static void quit(){
      JOptionPane.showMessageDialog(null, "Thank you for using the solution");
   }
}
