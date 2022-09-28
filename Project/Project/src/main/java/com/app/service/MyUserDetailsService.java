package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.pojos.User;


@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService{
	
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user=(User) this.userRepository.findByemail(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("Not Found");
		}
        return new MyUserDetails(user);

	
}
}
