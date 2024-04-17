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

   This is a File server (child) class extends to the Server(parent) class.
   */

import javax.swing.*;

public class FileServer extends Server {

   //Static variables
   public static final int MAX_USER = 5000;
   public static final int MIN_USER = 0;
   public static final int MAX_USAGE = 100;
   public static final double SERVER_USES_VALUE = 0.5;

   //Instance variables
   private int numOfFileServerAccount;
   private int numOfUser;

   //Constructors
   public FileServer(String serverName, String serverType, int hdCapacity, String operatingSys, int numOfFileServerAccount) {
      super(serverName, serverType, hdCapacity, operatingSys);
      setNumOfFileServerAccount(numOfFileServerAccount);
      numOfUser++;
   }

   // Mutator
   public void setNumOfFileServerAccount( int numOfFileServerAccount){
      if (numOfFileServerAccount <MIN_USER || numOfFileServerAccount >MAX_USER){
         JOptionPane.showMessageDialog(null,
                "Invalid value. Please enter a value between " + MIN_USER + " and " + MAX_USER);
      }
      this.numOfFileServerAccount = numOfFileServerAccount;
   }
   //Accessors
   public int getNumOfFileServerAccount(){
      return this.numOfFileServerAccount = numOfFileServerAccount;
   }

   //Special Purpose Methods
   //Override method in server
   public String getServerType() {
      return super.getServerType();
   }
   // Calculate the server Useases and return the value. Override method in server
   public double serverUsEase() {
      double serverUsage = this.numOfFileServerAccount * SERVER_USES_VALUE;
      return serverUsage > MAX_USAGE ? MAX_USAGE : serverUsage;
   }
   // This method return the String. Override method in server.
   public String toString(){
      return super.toString() + " Usease : " + String.format("%.2f", serverUsEase()) + " User : " + this.numOfFileServerAccount;
   }
}
