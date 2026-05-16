package com.example.cellphones_spring.security;

import com.example.cellphones_spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phone)
            throws UsernameNotFoundException {

        return userRepository.findByPhone(phone)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}