package com.meida.shaokaoshop.utils;

public class OrderUtils {

    public static String getOrderStatus(String status){


        //（-1已取消 0代支付 1代发货 2已发货 11退款中 12已退款 20已完成 21已评价）
        switch (status){
            case "-1":

               return "Cancel";
            case "0":

                return "To be Paid";
            case "1":

                return "Waiting for the Delivery";
            case "2":
                return "Waiting for Receiving";

            case "11":

                return "Refunding";
            case "12":

                return "Refund";
            case "20":

                return "Completed";
            case "21":

                return "已评价";

        }
        return "";

    }

}

