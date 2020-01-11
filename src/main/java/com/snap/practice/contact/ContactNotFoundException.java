package com.snap.practice.contact;

public class ContactNotFoundException extends IllegalArgumentException {
  private Long contactId;

  public ContactNotFoundException (Long id){
    this.contactId=id;
  }
  @Override
  public String getMessage() {
    return "Contact id: "+contactId+" not found!";
  }
}
