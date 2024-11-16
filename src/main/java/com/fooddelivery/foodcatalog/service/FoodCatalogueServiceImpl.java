package com.fooddelivery.foodcatalog.service;

import com.fooddelivery.foodcatalog.dto.FoodCataloguePage;
import com.fooddelivery.foodcatalog.dto.FoodItemDTO;
import com.fooddelivery.foodcatalog.dto.Restaurant;
import com.fooddelivery.foodcatalog.entity.FoodItem;
import com.fooddelivery.foodcatalog.mapper.FoodItemMapper;
import com.fooddelivery.foodcatalog.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FoodCatalogueServiceImpl implements FoodCatalogService{


    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {

        FoodItem fooItemEntity = foodItemRepo.save(FoodItemMapper.INSTANCE.foodItemDTOToEntity(foodItemDTO));
        return FoodItemMapper.INSTANCE.foodItemEntityToDTO(fooItemEntity);
    }

    @Override
    public FoodCataloguePage getRestaurantAndFoodList(Integer restaurantId) {
        //food item list
        //restaurant details
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMicroservice(restaurantId);
        return createFoodCataloguePage(foodItemList,restaurant);
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMicroservice(Integer restaurantId) {
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchRestaurantById/"+restaurantId,Restaurant.class);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        return foodItemRepo.findByRestaurantId(restaurantId);
    }
}
