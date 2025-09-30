package main.java.com.entity.client;

import java.util.UUID;

public record Client(String id, String number, String email) {

    public Client(String number , String email){
        this(UUID.randomUUID().toString().substring(0,10),number,email);
    }
}
