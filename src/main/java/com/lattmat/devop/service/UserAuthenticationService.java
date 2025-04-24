package com.lattmat.devop.service;

import com.lattmat.devop.dto.UserDto;
import com.lattmat.devop.entity.Users;
import com.lattmat.devop.repository.UserRepository;
import com.lattmat.devop.utility.GenricMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class UserAuthenticationService implements UserDetailsService {

    private final UserRepository userRepository;
    private final GenricMapperUtility genricMapper;

    @Autowired
    public UserAuthenticationService(UserRepository userRepository, GenricMapperUtility genricMapper){
        this.userRepository = userRepository;
        this.genricMapper = genricMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByNameIgnoreCase(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials"));
        return new User(user.getName(), user.getPassword(), new ArrayList<>());
    }

    public void registerUser(UserDto userDto) {
        Users user = genricMapper.mapToEntity(userDto, Users.class);
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        userRepository.save(user);
    }
}