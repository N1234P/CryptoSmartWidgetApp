package com.example.crypto_bsc_widget;

public class DexScreener {

    public static String findLargestLP(String response) {
        String updatedResponse = response;
        int largest = 0;
        String value;
        boolean initial = true;


        while(response.contains("liquidity")) {

            response = response.substring(response.indexOf("liquidity")+ 18);
            value = response.substring(0, response.indexOf(","));
            if(value.contains(".")) {
                value = value.substring(0, value.indexOf("."));
            }

            if(Integer.parseInt(value) > largest) {
                largest = Integer.parseInt(value);
                if(!initial) {
                    updatedResponse = updatedResponse.substring(updatedResponse.indexOf("liquidity"));
                }
                initial = false;
            }
        }

        return updatedResponse;
    }


    public static String extractTokenId(String response) {
        if(response.contains("error"))
            return "";

        try {
            response = response.substring(response.indexOf("symbol"));

            response = response.substring(response.indexOf(":") + 2);

            return response.substring(0, response.indexOf('"'));

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }



    public static double extractPrice(String response) {
        try {
            response = response.substring(response.indexOf("priceUsd"));
            response = response.substring(response.indexOf(":") + 2);
            response = response.substring(0, response.indexOf('"'));

            return Double.parseDouble(response);
        }
        catch(IndexOutOfBoundsException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String extractPercent(String response) {
        try {
            response = response.substring(response.indexOf("priceChange"));
            response = response.substring(response.indexOf("h24"));
            response = response.substring(response.indexOf(":")+1);
            response = response.substring(0, response.indexOf(","));
            return response;
        }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
            return "";
        }
    }
}
