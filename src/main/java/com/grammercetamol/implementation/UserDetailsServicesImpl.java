package com.grammercetamol.implementation;

import com.grammercetamol.utilities.Users;
import com.grammercetamol.utilities.repositories.UsersRepositories;
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
        System.out.println(users);
        return UserServices.build(users);
    }
}
