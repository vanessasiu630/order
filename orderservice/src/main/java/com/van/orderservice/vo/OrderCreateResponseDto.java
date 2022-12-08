package com.van.orderservice;

// import lombok.*;
public String status; //ACCEPTED or BADREQUEST

public String getstatus(){
    return status;
}
public String setstatus(String status){
    this.status = status;
}