package com.mow.restController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mow.entity.Meal;
import com.mow.repository.MealRepository;
import com.mow.service.MealService;

// Only Members are allowed to access

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/meal")
public class MealRestController {

	@Autowired
	private MealService mealService;

	@Autowired
	MealRepository mealRepository;

	@GetMapping("/search") // member
	@PreAuthorize("hasRole('MEMBER')")
	public ResponseEntity<?> search(@RequestParam("keyword") String keyword) {
		List<Meal> searchResults = mealService.search(keyword);

		if (searchResults.isEmpty()) {
			String message = "No results found. Please try again.";
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		} else {
			return ResponseEntity.ok(searchResults);
		}
	}

	@GetMapping("/mealdetails/{meal_id}")
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'PARTNER', 'MEMBER')")
	public Optional<Meal> viewMealDetails(@PathVariable Long meal_id) {
		return mealRepository.findById(meal_id);
	}

	@GetMapping("/showMenu") // member
	@PreAuthorize("hasAnyRole('ADMINISTRATOR', 'MEMBER')")
	public List<Meal> showMenu() {
		List<Meal> menu = mealService.showMenu();
		return menu;
	}

	// FILTER PLANT & MEAT, HOT & FROZEN
	@GetMapping(value = "/showMenu/plant")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getPlantMenu() {
		List<Meal> menu = mealRepository.displayPlantBased();
		return menu;
	}

	@GetMapping(value = "/showMenu/meat")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getMeatMenu() {
		List<Meal> menu = mealRepository.displayMeatBased();
		return menu;
	}

	@GetMapping(value = "/showMenu/plant/hot")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getHotPlantMenu() {
		List<Meal> menu = mealRepository.displayHotPlantBased();
		return menu;
	}

	@GetMapping(value = "/showMenu/meat/hot")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getHotMeatMenu() {
		List<Meal> menu = mealRepository.displayHotMeatBased();
		return menu;
	}

	@GetMapping(value = "/showMenu/plant/frozen")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getFrozenPlantMenu() {
		List<Meal> menu = mealRepository.displayFrozenPlantBased();
		return menu;
	}

	@GetMapping(value = "/showMenu/meat/frozen")
	@PreAuthorize("hasRole('MEMBER')")
	public List<Meal> getFrozenMeatMenu() {
		List<Meal> menu = mealRepository.displayFrozenMeatBased();
		return menu;
	}

	@GetMapping("/images/{meal_id}/{meal_photo:.+}")
	@PreAuthorize("hasRole('MEMBER')")
	public ResponseEntity<Resource> getImage(@PathVariable String meal_id, @PathVariable String meal_photo) {
		try {
			Path imagePath = Paths.get("src/main/resources/static/images/meals/" + meal_id + "/" + meal_photo);
			Resource imageResource = new UrlResource(imagePath.toUri());

			if (imageResource.exists() && imageResource.isReadable()) {
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageResource);
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (IOException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/add_meal")
	@PreAuthorize("hasRole('ADMINISTRATOR')")
	public ResponseEntity<String> addNewMeal(@RequestParam("image") MultipartFile image, @ModelAttribute Meal meal) {
		try {
			String imageName = UUID.randomUUID().toString() + "_" + StringUtils.cleanPath(image.getOriginalFilename());

			// Set meal details
			meal.setMeal_photo(imageName);

			// Save the meal
			Meal savedMeal = mealService.saveMeal(meal);

			// Path of the image
			String imagePath = "./src/main/resources/static/images/meals/" + savedMeal.getMeal_id();

			// Save the image file
			Path uploadPath = Paths.get(imagePath);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			try (InputStream inputStream = image.getInputStream()) {
				Path filePath = uploadPath.resolve(imageName);
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new IOException("Cannot save uploaded file: " + imageName);
			}

			// Set the image path for the meal
			meal.setMeal_img_path("/images/meals/" + savedMeal.getMeal_id() + "/" + savedMeal.getMeal_photo());
			mealService.saveMeal(meal);

			return ResponseEntity.ok("Meal successfully added");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding meal");
		}
	}

}
