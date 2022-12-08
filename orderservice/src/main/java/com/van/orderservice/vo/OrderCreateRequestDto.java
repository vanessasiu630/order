
package com.van.orderservice;

// import lombok.*;
public String clordi; 
public <MessageHeader> msgType;
public <Instrument> instrument;
public String side;
public String transactTime;
public <OrderQtyData> orderQtyData;
public String ordType; 

public String getclordi(){
    return clordi;
}
public String setclordi(String clordi){
    this.clordi = clordi;
}
public <MessageHeader> getmsgType(){
    return msgType; 
}
public <MessageHeader> setmsgType(<MessageHeader> msgType){
    this.msgType = msgType;
}
public <Instrument> getinstrument(){
    return instrument;
}
public <Instrument> setinstrument(<Instrument> instrument){
    this.instrument = instrument;
}
public String getside(){
    return side;
}
public String setside(String side){
    this.side = side;
}
public String gettransactTime(){
    return transactTime; 
}
public String settransactTime(String transactTime){
    this.transactTime = transactTime;
}
public <OrderQtyData> getorderQtyData(){
    return orderQtyData;
}
public <OrderQtyData> setorderQtyData(<OrderQtyData> orderQtyData){
    this.orderQtyData = orderQtyData; 
}
public String getordType(){
    return getordType;
}
public String setordType(String ordType){
    this.ordType = ordType; 
}
