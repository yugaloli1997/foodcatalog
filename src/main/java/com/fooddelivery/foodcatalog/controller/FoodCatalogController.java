package com.fooddelivery.foodcatalog.controller;


import com.fooddelivery.foodcatalog.dto.FoodCataloguePage;
import com.fooddelivery.foodcatalog.dto.FoodItemDTO;
import com.fooddelivery.foodcatalog.service.FoodCatalogueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogController {

    @Autowired
    FoodCatalogueServiceImpl foodCatalogueService;

    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){

        FoodItemDTO food = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(food, HttpStatus.ACCEPTED);
    }

    @GetMapping("/fetchRestaurantAndFoodListById/{id}")
    public ResponseEntity<FoodCataloguePage> getRestaurantAndFoodDetailsById(@PathVariable Integer id){

        FoodCataloguePage foodList = foodCatalogueService.getRestaurantAndFoodList(id);

        return new ResponseEntity<>(foodList,HttpStatus.OK);
    }

}
