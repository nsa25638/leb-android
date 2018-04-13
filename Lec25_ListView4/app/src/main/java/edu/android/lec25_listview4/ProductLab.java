package edu.android.lec25_listview4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-21.
 */

public class ProductLab {
    private static final int[] IMAGE_IDS = {
            R.drawable.pr1, R.drawable.pr2, R.drawable.pr3,
            R.drawable.pr4, R.drawable.pr5, R.drawable.pr6,
            R.drawable.pr7, R.drawable.pr8, R.drawable.pr9
    };

    private List<Product> productList;
    private static ProductLab instance = null;

    private ProductLab() {
        productList = new ArrayList<>();
        makeDummyData();
    }

    private void makeDummyData() {
        for (int i = 0; i < 100; i++) {
            Product product = new Product(
                    "Product Name " + i,
                    i,
                    "Product Description " + i,
                    IMAGE_IDS[i % IMAGE_IDS.length]);
            productList.add(product);
        }
    }

    public static ProductLab getInstance() {
        if (instance == null) {
            instance = new ProductLab();
        }
        return instance;
    }

    public List<Product> getProductList() {
        return productList;
    }
}
