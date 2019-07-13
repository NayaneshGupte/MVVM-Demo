package com.test.shoppingapp.shoppinglist;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.SOURCE)
@StringDef({ProductStatus.SOLD_OUT,
        ProductStatus.ON_SALE
})
public @interface ProductStatus {
    String SOLD_OUT = "sold_out";
    String ON_SALE = "on_sale";
}
