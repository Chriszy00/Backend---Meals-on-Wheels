package com.mow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mow.entity.Meal;
import com.mow.repository.MealRepository;

@Service
public class MealService {

	
	@Autowired
	MealRepository mealRepository;

	public MealService(MealRepository mealsRepository) {
		this.mealRepository = mealsRepository;
	}

	public List<Meal> search(String keyword) {
		return mealRepository.search(keyword);
	}

	public List<Meal> showMenu() {
		return mealRepository.findAll();
	}

	public Meal saveMeal(Meal meal) {

		return mealRepository.save(meal);
	}

	public List<Meal> displayPlantMeals() {
		return mealRepository.displayPlantBased();
	}

	public List<Meal> displayMeatMeals() {
		return mealRepository.displayMeatBased();
	}

	public List<Meal> displayHotPlants() {
		return mealRepository.displayHotPlantBased();
	}

	public List<Meal> displayHotMeat() {
		return mealRepository.displayHotMeatBased();
	}

	public List<Meal> displayFrozenPlants() {
		return mealRepository.displayFrozenPlantBased();
	}

	public List<Meal> displayFrozenMeat() {
		return mealRepository.displayFrozenMeatBased();
	}

}
