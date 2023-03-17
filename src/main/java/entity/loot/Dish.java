package entity.loot;

import enums.FoodEffect;

import java.util.ArrayList;
import java.util.List;

public class Dish {

    private List<Food> ingredients = new ArrayList<>(5);

    private FoodEffect effect;
}
