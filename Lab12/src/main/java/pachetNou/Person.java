package pachetNou;

import org.example.Test;

public class Person {
    private String name;
    private String address;
    private int phoneNumber;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test
    public void setAddress(String address) {
//        if(address == null){
//            throw new IllegalArgumentException("Adress can't be null bozo!");
//        }
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
