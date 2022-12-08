package com.van.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@SpringBootApplication
public class OrderserviceApplication {

  public static void main(String[] args) {
    SpringApplication.run(OrderserviceApplication.class, args);

    OrderCreateRequestDto orderCreateRequestDto ;
    OrderCreateResponseDto orderCreateResponse;
    OrderController orderController;
    GatewayController gatewayController;
    public static Connection conn = null;

    Boolean flag = false;
    // connect server
    conn = getConnection();
    // connect db 

    // validate the order request and return the appropriate HTTP response
    request.setclordi("ID00099");
    request.setinstrument("list");
    request.setmsgType("D");
    request.setorderQtyData("list");
    request.setordType("STOP");
    request.setside("1");
    request.settransactTime("08:00");
    
    orderCreateResponse = orderController.create(request); 

    if (orderCreateResponse.getstatus() != null){
      if (orderCreateResponse.getstatus() == "ACCEPTED") {
        flag = InsertOrder(orderCreateResponse);    
        
        if (flag){
          InvokeGatewayService(orderCreateResponse)
        }
    }
  }
  
public static Boolean InsertOrder(OrderCreateRequestDto request) {

    // Create an order record in the database2 
    String sql = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    String _sql = null;
    PreparedStatement _stmt = null;
    ResultSet _rs = null;
    
    try {
    //check if CLORDI exists in db
    sql = "SELECT CLORDI FROM EVORD WHERE CLORDI = ?"; // Assume "EVORD" : Easy View Order DB Table
    System.out.println("sql= [" + sql + "]");
    stmt = conn.preparedStatement(sql);
    rs = stmt.executeQuery();
    System.out.println("OrderService - sql done");   

    if (rs.next() == false) {
      _sql = "INSERT INTO EVORD (CLORDI, INST, MSGTYP, ORDQTYDAT, ORDTYP, SIDE, TXNTIM, STA) VALUES (?,?,?,?,?,?,?)";
      System.out.println("sql= [" + _sql + "]");
      _stmt = conn.preparedStatement(_sql);
      _rs = _stmt.executeQuery();
          
      _stmt.setString(1,request.getclordi());
      _stmt.setString(2,request.getinstrument());
      _stmt.setString(3,request.getmsgType());
      _stmt.setString(4,request.getorderQtyData());
      _stmt.setString(5,request.getordType());
      _stmt.setString(6,request.getside());
      _stmt.setString(7,request.gettransactTime());

      // Mark the order as ACCEPTED
      _stmt.setString(8,"ACCEPTED");

      _stmt.executeUpdate();
      _stmt.close();
      _stmt = null;
      System.out.println("OrderService - insert new order");
    }
      System.out.println("OrderService - ID existed");       
      return true; 
    }
    catch (Exception e){
      e.printStackTrace(); 
      return false;
  }
}
    
public static String InvokeGatewayService(OrderCreateRequestDto request) {
  String _sql = null;
  PreparedStatement _stmt = null;
  ResultSet _rs = null;
  // connect server
    conn = getConnection();
  // connect db..

  // Pass order to Gateway Service
    request.setclordi("ID00099");
    request.setinstrument("list");
    request.setmsgType("D");
    request.setorderQtyData("list");
    request.setordType("STOP");
    request.setside("1");
    request.settransactTime("08:00");
    
    orderCreateResponse = gatewayController.create(request); 
    
    if (orderCreateResponse.getstatus() != null){
      if (orderCreateResponse.getstatus() == "ACCEPTED") {
      _sql = "UPDATE EVORD SET GSSTA = ? WHERE CLORDI = ?"; 
      System.out.println("sql= [" + _sql + "]");
      _stmt = conn.preparedStatement(_sql);
      _rs = _stmt.executeQuery();
          
      _stmt.setString(1,"SENT");
      _stmt.setString(2,request.getclordi());

      _stmt.executeUpdate();
      _stmt.close();
      _stmt = null;
      System.out.println("OrderService - insert new order");
      }
    }
}

public static getConnection(){
  // .. get connection 
}

}

/* 
Order Controller -> Expose an end point to handle new order requests1 (= CONNECT HTTP POST)
Gateway Controller -> Expose an end point to handle new order requests1 (= CONNECT HTTP POST)

OrderserviceApplication -> 
validate the order request and return the appropriate HTTP response
Create an order record in the database2 and mark the order as ACCEPTED
Pass order to Gateway Service

Gateway Service -> Mark the order as SENT when order is acknowledged by the Gateway

*/ 