package com.contextidea.tabiangifts.util;

import com.contextidea.tabiangifts.models.Product;

import java.math.BigDecimal;
import java.util.HashMap;




/**
 * Created by User on 2/8/2018.
 */

public class Prices {

    private static final HashMap<String, BigDecimal> PRICES;
    static
    {
        PRICES = new HashMap<String, BigDecimal>();
        Products products = new Products();
        for(Product product : products.PRODUCTS){
            PRICES.put(String.valueOf(product.getSerial_number()), product.getPrice());
        }
    }



    public static HashMap<String, BigDecimal> getPrices(){
        return  PRICES;
    }
}
