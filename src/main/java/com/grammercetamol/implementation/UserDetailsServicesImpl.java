package com.grammercetamol.implementation;

import com.grammercetamol.repositories.UsersRepositories;
import com.grammercetamol.utilities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServicesImpl implements UserDetailsService {
    @Autowired
    UsersRepositories usersRepositories;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepositories.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        return UserServices.build(users);
    }
}
