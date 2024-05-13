package com.sport.trade.wallet.contracts;

public class StandardCharsets {
    public static void main(String[] args) {
        boolean isAdmin = true;
        // Correct code should be this
        // if (isAdmin) {
        //     doFirstThing();
        // }
        // doSecondThing();

        // Error code
        if (isAdmin)
        doFirstThing();
        doSecondThing();
        
        String s = getPropertyValue();
        byte[] b = s.getBytes(java.nio.charset.StandardCharsets.UTF_8);
    }

    private static void doFirstThing() {
        System.out.println("Doing the first thing because isAdmin is true.");
    }

    private static void doSecondThing() {
        System.out.println("Doing the second thing.");
    }

    private static String getPropertyValue() {
        return "Your property value here";
    }
}
