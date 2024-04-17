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

   This is a Webserver (child) class extends to the Server(parent) class.
   */

public class WebServer extends Server {

   //variables
   public static final int MAX_SERVER_NAME_ALLOW = 4;
   private String[] listOfWebServer;

   //Constructors
   public WebServer(String name, String serverType, int hdCapacity, String operatingSys, String[] listOfWebServer){
      super(name, serverType, hdCapacity, operatingSys);
      this.listOfWebServer = new String [MAX_SERVER_NAME_ALLOW];
      setListOfWebServer(listOfWebServer);
   }
   //Accessors
   public String[] getListOfWebServer() {
      return copyListOfWebServer(listOfWebServer);
   }

   //Mutators

   public String[] copyListOfWebServer(String [] listOfWebServer){
      String [] copy = new String [listOfWebServer.length];
      for (int i = 0; i<copy.length; i++){
         copy[i] = listOfWebServer[i];
      }
      return copy;
   }


   public void setListOfWebServer(String[] arr) {
      if (arr.length > 4) {
         throw new IllegalArgumentException("A maximu of 4 name allowed");
      }
      listOfWebServer = copyListOfWebServer((arr));
   }



   //Special Purpose Methods

   public String listOfWebServerPrint(){
      String list = "";
      for (int i=0; i< listOfWebServer.length; i++){
         if (listOfWebServer[i]!=null) {
            list += listOfWebServer[i];
            if (i < listOfWebServer.length-1){
               list += ", ";
            }
         }
      }
      return list;
   }

// This method counts the web-server and return the number of web-servers.
   public int webServerSoftCount(){
      int count = 0;
      for (int i=0; i< listOfWebServer.length; i++){
         if (listOfWebServer[i]!=null){
            count++;
         }
      }
      return count;
   }
   @Override
   public String getServerType() {
      return super.getServerType();
   }
   // Calculate the server usease and return the value.
   public double serverUsEase(){
      return webServerSoftCount()* 20;
   }
   // This method return the String.
   public String toString(){
      return super.toString() + " Usease : " + String.format("%.2f", serverUsEase()) + " Software: " + listOfWebServerPrint();
   }
}