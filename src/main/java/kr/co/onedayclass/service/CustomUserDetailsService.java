package kr.co.onedayclass.service;

import kr.co.onedayclass.dto.CustomUserDetails;
import kr.co.onedayclass.entity.UserEntity;
import kr.co.onedayclass.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity useData = userRepository.findByUsername(username);
        if (useData != null){
            return new CustomUserDetails(useData);
        }
        return null;
    }
}
