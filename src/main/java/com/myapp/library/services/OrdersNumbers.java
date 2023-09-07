package com.myapp.library.services;

import java.util.Random;

public class OrdersNumbers {
    public static int randomNumber(){
        Random random = new Random();
        return random.nextInt(90000) + 10000;
    }
}
