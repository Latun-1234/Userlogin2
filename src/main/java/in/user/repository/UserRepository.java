package in.user.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	@Query(value ="select u from User u where u.mobile= :mobile")
	Optional<User> findByMobile(@Param("mobile") String mobile);

	List<User> findByUserType(String userType);	

	List<User> findByDateOfBirthBetween(LocalDate startDate, LocalDate endDate);

	// List<User> findAll();

	void deleteById(Long id);

	/*
	 * @Query("Delete ") User deleteByUserId(Long id);
	 */
}
