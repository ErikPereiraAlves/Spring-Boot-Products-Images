package com.erikalves.application.utils;

import java.util.UUID;

public class UniqueIdGenerator {


    public static String getUniqueRandomId() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }


    public static void main(String[] args) {

        String randomUUIDString = getUniqueRandomId();
        System.out.println("Random UUID String = " + randomUUIDString);

    }
}
