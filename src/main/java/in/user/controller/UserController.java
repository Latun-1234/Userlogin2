package in.user.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.user.entity.User;
import in.user.exception.ResourceNotFoundException;
import in.user.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private final UserRepository userRepository;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(path = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User addUser(@RequestBody @Valid User user) {
		return userRepository.save(user);
	}

	@PutMapping(path = "/modify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) throws Exception {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		// Update the fields of the user entity user.setName(updatedUser.getName());
		user.setDateOfBirth(updatedUser.getDateOfBirth());
		user.setEmail(updatedUser.getEmail());
		user.setMobile(updatedUser.getMobile());
		user.setUserType(updatedUser.getUserType());

		return userRepository.save(user);
	}

	@GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getUser(@PathVariable Long id) throws Exception {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
	}

	@ResponseStatus(value = HttpStatus.OK)
	@DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "user delete for this id : " + id;
	}

	@GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@GetMapping(path = "/mobile/{mobile}", produces =MediaType.APPLICATION_JSON_VALUE)
	  public Optional<User>getUserByMobile(@PathVariable("mobile") String mobile) { 
	 return  userRepository.findByMobile(mobile);
	}

	public List<User> getAllUsers(@RequestParam(required = false) String userType,
			@RequestParam(required = false) Integer ageGrater, @RequestParam(required = false) Integer ageLessthan) {
		if (userType != null) {
			return userRepository.findByUserType(userType);
		} else if (ageGrater != null && ageLessthan != null) {
			LocalDate startDate = LocalDate.now().minusYears(ageLessthan);
			LocalDate endDate = LocalDate.now().minusYears(ageGrater);
			return userRepository.findByDateOfBirthBetween(startDate, endDate);
		} else if (ageGrater != null) {
			LocalDate startDate = LocalDate.now().minusYears(ageGrater);
		}
		return new ArrayList<User>();
	}
}
