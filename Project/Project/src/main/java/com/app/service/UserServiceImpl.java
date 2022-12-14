package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.AddressRepository;
import com.app.dao.CategoryRepository;
import com.app.dao.OrdersRepository;
import com.app.dao.SupplierRepository;
import com.app.dao.UserRepository;
import com.app.dto.LoginRequest;
import com.app.dto.UserDTO;
import com.app.pojos.Address;
import com.app.pojos.Category;
import com.app.pojos.Orders;
import com.app.pojos.Role;
import com.app.pojos.Supplier;
import com.app.pojos.User;


@Service
@Transactional
public class UserServiceImpl implements IUserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AddressRepository addressrepo;
	
	@Autowired
	private CategoryRepository cateRepo;
	
	@Autowired
	private SupplierRepository supRepo;
	
	@Autowired
	private OrdersRepository orderRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public User authenticateUser(LoginRequest loginRequest) {
		PasswordEncoder encoder=new BCryptPasswordEncoder();
		
		User u=userRepo.findByemail(loginRequest.getEmail());
		
		System.out.println(u.getEmail()+"--------------");
		
		String rawpassword=u.getPassword();
		System.out.println(u.getPassword()+"--------------");

		String pass=loginRequest.getPassword();
		
		loginRequest.setPassword(pass);
		
		System.out.println(pass+"--------------");

		if(encoder.matches(pass, rawpassword)) {
		return userRepo.authenticateUser(loginRequest.getEmail());}
		return null;
	}
	
	
	

	
	@Override
	public String createAccount(User user) {
		User u = userRepo.save(user);
		u.setPassword(encoder.encode(user.getPassword()));
		userRepo.save(u);
		Address add = new Address();
		add.setCity("Pune");
		add.setState("Maharashtra");
		add.setCurrentUser(u);
		addressrepo.save(add);
		return "SignUp successfully";
	}
	
	@Override
	public User editProfile(int userId, UserDTO userDTO) {
		User user = userRepo.findById(userId).get();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPhone(userDTO.getPhone());
		return user;
	}
	
	@Override
	public String changePassword(int userId, String pwd) {
		User u = userRepo.findById(userId).get();
		u.setPassword(pwd);
		return "Password Changed successfully";
	}
	
	@Override
	public Address getAddress(int userId) {
		return addressrepo.findById(userId).get();
	}
	
	@Override
	public String editAddress(int userId, Address address) {
		Address add = addressrepo.findById(userId).get();
		System.out.println("address : "+add);
		if(add != null) {
		add.setArea(address.getArea());
		add.setCity(address.getCity());
		add.setFlatNo(address.getFlatNo());
		add.setPinCode(address.getPinCode());
		add.setSocietyName(address.getSocietyName());
		add.setState(address.getState());
		}
		return "Address Changed successfully";
	}
	
	@Override
	public List<User> getAllSupplier() {
		
		return userRepo.findByRole(Role.SUPPLIER);
	}
	
	@Override
	public List<User> getAllDeliveryBoy() {
		return userRepo.findByRole(Role.DELIVERY_BOY);
	}
	
	@Override
	public int addSupplierAccount(String categoryName, User user) {
		
		User u = userRepo.save(user);
		Address add = new Address();
		add.setCity("Pune");
		add.setState("Maharashtra");
		add.setCurrentUser(u);
		addressrepo.save(add);
		
		Category c = new Category();
		c.setCategoryName(categoryName);
		Category cat = cateRepo.save(c);
		
		Supplier supp = new Supplier();
		supp.setCurrentUser(u);
		supp.setSupplierCategory(cat);
		supRepo.save(supp);
		return supp.getCurrentUser().getId();
	}
	
	@Override
	public Address getAddressDetails(int orderId) {
		Orders od = orderRepo.findById(orderId).get();
		User u = od.getSelectedCustomer();		
		return addressrepo.findById(u.getId()).get();
	}
	
	@Override
	public User getUserDetails(int oId) {
		Orders o = orderRepo.findById(oId).get();
		System.out.println("User : "+o.getSelectedCustomer());
		return o.getSelectedCustomer();
	}


	
}
