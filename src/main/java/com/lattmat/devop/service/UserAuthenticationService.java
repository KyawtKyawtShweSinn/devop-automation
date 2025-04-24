package com.lattmat.devop.service;

import com.lattmat.devop.dto.UserDto;
import com.lattmat.devop.entity.Users;
import com.lattmat.devop.repository.UserRepository;
import com.lattmat.devop.utility.GenricMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
                .orElseThrow(() -> new UsernameNotFoundException("Invalid Credentials."));
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().toUpperCase()));

        return new User(user.getName(), user.getPassword(), authorities);
       // return new User(user.getName(), user.getPassword(), new ArrayList<>());
    }

    public void registerUser(UserDto userDto) {
        Users user = genricMapper.mapToEntity(userDto, Users.class);
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        user.setJoinDate(new Date());
        user.setActive(true);
        userRepository.save(user);
    }
}