package com.fooddelivery.foodcatalog.mapper;

import com.fooddelivery.foodcatalog.dto.FoodItemDTO;
import com.fooddelivery.foodcatalog.entity.FoodItem;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FoodItemMapper {
    FoodItemMapper INSTANCE= Mappers.getMapper(FoodItemMapper.class);

    FoodItem foodItemDTOToEntity(FoodItemDTO foodItemDTO);

    FoodItemDTO foodItemEntityToDTO(FoodItem foodItem);

}
