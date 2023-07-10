package com.mow.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mow.entity.Meal;


public interface MealRepository extends JpaRepository<Meal, Long> {

	@Query("SELECT meal FROM Meal meal WHERE meal.meal_name LIKE %:keyword%" + " OR meal.meal_desc LIKE %:keyword%"
			+ " OR meal.meal_nutri LIKE %:keyword%" + " OR meal.type LIKE %:keyword%" + " OR meal.category LIKE %:keyword%")
	
	
	public List<Meal> search(@Param("keyword") String keyword);

	@Query("SELECT meal FROM Meal meal WHERE category = 'Plant Based'")
	public List<Meal> displayPlantBased();

	@Query("SELECT meal FROM Meal meal WHERE category = 'Meat Based'")
	public List<Meal> displayMeatBased();

	@Query("SELECT meal FROM Meal meal WHERE category = 'Plant Based' AND type = 'Hot' ")
	public List<Meal> displayHotPlantBased();

	@Query("SELECT meal FROM Meal meal WHERE category = 'Plant Based' AND type = 'Frozen' ")
	public List<Meal> displayFrozenPlantBased();

	@Query("SELECT meal FROM Meal meal WHERE category = 'Meat Based' AND type = 'Hot' ")
	public List<Meal> displayHotMeatBased();

	@Query("SELECT meal FROM Meal meal WHERE category = 'Meat Based' AND type = 'Frozen' ")
	public List<Meal> displayFrozenMeatBased();

	public Optional<Meal> findById(Long mealId);

}
