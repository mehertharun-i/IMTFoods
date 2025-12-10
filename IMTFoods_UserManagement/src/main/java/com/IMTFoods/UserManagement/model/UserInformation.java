package com.IMTFoods.UserManagement.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.IMTFoods.UserManagement.utils.Gender;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="user")
public class UserInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;
	
	@Column(nullable = false)
	private String userFirstName;
	
	@Column(nullable = false)
	private String userLastName;
	
	private LocalDate userDateOfBirth;
	
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(nullable = false, unique = true)
	private String userEmail;
	
	private String userPassword;
	
	@Column(nullable = false, unique = true)
	private String userPhoneNumber;
	
	private boolean isAccountDeleted;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "user_information_id")
	@ToString.Exclude
	private List<UserAddressInformation> userAddressInformation; 
	
	@Column(nullable = false)
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_authorities", joinColumns = {@JoinColumn(name="userId")}, inverseJoinColumns = {@JoinColumn(name="authoritiesId")})
	@ToString.Exclude
	private List<Authorities> roles;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "userInformationId")
	@ToString.Exclude
	@Builder.Default
	private List<FavouriteRestaurant> favouriteRestaurant = new ArrayList<>();
	
	private List<Long> userOrdersDetials;
	
	private List<Integer> userSavedPayementDetails;

	public UserInformation(String userFirstName, String userLastName, LocalDate userDateOfBirth, Gender gender,
			String userEmail, String userPassword, String userPhoneNumber, boolean isAccountDeleted,
			List<UserAddressInformation> userAddressInformation, List<Authorities> roles,
			List<FavouriteRestaurant> favouriteRestaurant, List<Long> userOrdersDetials,
			List<Integer> userSavedPayementDetails) {
		super();
		this.userFirstName = userFirstName;
		this.userLastName = userLastName;
		this.userDateOfBirth = userDateOfBirth;
		this.gender = gender;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userPhoneNumber = userPhoneNumber;
		this.isAccountDeleted = isAccountDeleted;
		this.userAddressInformation = userAddressInformation;
		this.roles = roles;
		this.favouriteRestaurant = favouriteRestaurant;
		this.userOrdersDetials = userOrdersDetials;
		this.userSavedPayementDetails = userSavedPayementDetails;
	}
	
	
//	public void addAddress(UserAddressInformation userAddressInformation) {
//		this.userAddressInformation.add(userAddressInformation);
//		userAddressInformation.setUserInformation(this);;
//	}
//	
//	public void removeAddress(UserAddressInformation userAddressInformation) {
//		this.userAddressInformation.remove(userAddressInformation);
//		userAddressInformation.setUserInformation(null);
//	}
	

}
