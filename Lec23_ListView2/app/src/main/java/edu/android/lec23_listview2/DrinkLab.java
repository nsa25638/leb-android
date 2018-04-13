package edu.android.lec23_listview2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-21.
 * Drink의 리스트를 관리하는 DAO와 유사한 클래스 - Singleton 패턴
 */

public class DrinkLab {

    private List<Drink> drinkList;
    private static DrinkLab instance = null;

    private DrinkLab() {
        drinkList = new ArrayList<>();
        makeDummyData();
    }

    // ArrayList에 데이터를 만듬
    private void makeDummyData() {
        Drink d1 = new Drink(1, "아메리카노", null, 1000, -1);
        drinkList.add(d1);

        Drink d2 = new Drink(2, "라떼", null, 1500, -1);
        drinkList.add(d2);

        Drink d3 = new Drink(1, "카푸치노", null, 1500, -1);
        drinkList.add(d3);

        Drink d4 = new Drink(1, "카페모카", null, 2000, -1);
        drinkList.add(d4);
    }

    public static DrinkLab getInstance() {
        if (instance == null) {
            instance = new DrinkLab();
        }
        return instance;
    }

    public List<Drink> getDrinkList() {
        return drinkList;
    }
}









