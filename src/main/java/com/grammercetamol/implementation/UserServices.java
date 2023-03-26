package com.grammercetamol.implementation;

import com.grammercetamol.utilities.Permissions;
import com.grammercetamol.utilities.Roles;
import com.grammercetamol.utilities.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class UserServices implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String otherName;
    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserServices(
            Long id,
            String firstName,
            String lastName,
            String otherName,
            String email,
            String password,
            Collection<? extends GrantedAuthority> authorities) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otherName = otherName;
        this.username = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserServices build(Users users) {
        Set<Permissions> permissions = new HashSet<>(Enum.valueOf(Roles.class, users.getRole()).permissions());
        Set<GrantedAuthority> authorities1 =
                permissions
                        .stream()
                        .map(
                                permissions1 -> new SimpleGrantedAuthority(permissions1.getPermissions())
                        ).collect(Collectors.toSet());
        return new UserServices(
                users.getId(),
                users.getFirstName(),
                users.getLastName(),
                users.getOtherName(),
                users.getEmail(),
                users.getPassword(),
                authorities1
        );
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
