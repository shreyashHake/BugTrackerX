package com.springBoot.eBugTracker.service;

import com.springBoot.eBugTracker.entity.JwtRequest;
import com.springBoot.eBugTracker.entity.JwtResponse;
import com.springBoot.eBugTracker.entity.User;
import com.springBoot.eBugTracker.entity.customer.CustomerProfile;
import com.springBoot.eBugTracker.repository.IUserRepository;
import com.springBoot.eBugTracker.repository.customer.CustomerProfileRepo;
import com.springBoot.eBugTracker.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private IUserRepository IUserRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);

        final User user = IUserRepository.findById(userName).get();

        final UserDetails userDetails = loadUserByUsername(userName);
        final String newGeneratedToken = jwtUtil.generateToken(userDetails);

        return new JwtResponse(user, newGeneratedToken);
    }



    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = IUserRepository.findById(userName).get();
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user)
            );
        } else {
            throw new UsernameNotFoundException("UserName is not valid!");
        }
    }

    private Set getAuthorities(User user) {
        Set authorities = new HashSet();
        user.getUserRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        } catch (DisabledException e) {
            throw new Exception("User is disabled");
        } catch (BadCredentialsException e) {
            throw new Exception("Bad Credentials from users");
        }
    }
}
