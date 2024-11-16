package com.fooddelivery.foodcatalog.service;

import com.fooddelivery.foodcatalog.dto.FoodCataloguePage;
import com.fooddelivery.foodcatalog.dto.FoodItemDTO;

public interface FoodCatalogService {
    FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO);

    FoodCataloguePage getRestaurantAndFoodList(Integer id);
}
