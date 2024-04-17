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

   This is a DDC Server Class.
   */

public abstract class  Server {

   //Static variables
   final static int MAX_SERVER_CAPACITY = 200;
   public static final int MAX_LEVEL_USE = 100;
   public static final int MIN_LEVEL_USE = 0;

   //Instance variables
   private String serverName;
   private String serverType;
   private String operatingSys;
   private String nameList[] = new String[MAX_SERVER_CAPACITY];
   private int hdCapacity;
   private static int numOfServer = 0;

   //Constructor
   public Server(String serverName, String serverType, int hdCapacity, String operatingSys) {
      if ((serverName == null || serverName.equals("")) && equals(serverName)) {
         throw new IllegalArgumentException("server name is same");
      }
      if (hdCapacity < 0) {
         throw new IllegalArgumentException("Hard Capacity must be greater than 0");
      }
      setServerName(serverName);
      setServerType(serverType);
      setHdCapacity(hdCapacity);
      nameList[numOfServer] = this.serverName;
      setOperatingSys(operatingSys);
      numOfServer++;
   }

   //Accessors
   public String getServerName() {
      return this.serverName;
   }

   public String getServerType() {
      return this.serverType;
   }

   public int getHdCapacity() {
      return this.hdCapacity;
   }

   public String getOperatingSys() {
      return this.operatingSys;
   }

   public static int getNumOfServer() {
      return numOfServer;
   }

   //Mutator

   public void setServerName(String serverName) {
      if ((serverName == null || serverName.equals("")) && equals(serverName)) {
         throw new IllegalArgumentException("serverName name same");
      }
      this.serverName = serverName;
   }
  
   public void setServerType(String serverType) {
      this.serverType = serverType;
   }

   public void setHdCapacity(int hdCapacity) {
      if (hdCapacity < 0) {
         throw new IllegalArgumentException("Hard Capacity must be greater than 0");
      }
      this.hdCapacity = hdCapacity;
   }

   public void setOperatingSys(String operatingSys) {
      this.operatingSys = operatingSys;
   }
   // Abstract method
   public abstract double serverUsEase();

   //Special Purpose Methods
   // This method will check whether the serverName is equals or not. Override method in object.
   public boolean equals(Object o) {
      if(this.getClass() == o.getClass()){
         Server temp = (Server)o;
         String name1 = this.serverName;
         String name2 = temp.serverName;
         if(name1.equals(name2)){
            return true;
         } else{
            return false;
         }
      } 
      return false;
   
   }
   // This method return the String. Override method in object and over ridden method in file server and web server.
   public String toString() {
      return "Name : " + this.serverName + "  Type:  " + this.serverType +
              "  OS : " + this.operatingSys + "  HD :" + this.hdCapacity;
   }
}