package in.user.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Table(name = "user")
@SQLDelete(sql="UPDATE user SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
//@FilterDef(name="deletedUserFilter",parameters=@ParamDef(name="isDeleted",type="boolean"))
@Filter(name="deletedUserFilter",condition="deleted=:isDeleted")
@Data
public class User  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotEmpty(message = "field is required.")
	@Size(min = 4, max = 30, message = "min 4 chars & max 30 chars allowed")
	private String name;

	@Column(name = "date_of_birth")
	@NotNull(message = "INVALI_DATE_FORMAT")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm/dd/yyyy")
	private String dateOfBirth;

	@NotEmpty(message = "field is required.")
	@Email(message = "please enter valid email adress.")
	@Column(unique = true)
	private String email;

	// @NotEmpty(message = "field is required.")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "please pass only numberic/digits ")
	@NotEmpty(message = "mobile number can't be empty")
	@Column(unique = true)
	private String mobile;
	@NotNull(message = "possible user types are Developer,HR,Manager")
	@Column(name = "user_type")
	private String userType;
	
	private boolean deleted=Boolean.FALSE;;

	
}

