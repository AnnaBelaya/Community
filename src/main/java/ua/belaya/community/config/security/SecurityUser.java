package ua.belaya.community.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ua.belaya.community.domain.Student;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Anna Belaya
 */

public class SecurityUser extends Student implements UserDetails{

    public SecurityUser(Student student) {
        this.setEmail(student.getEmail());
        this.setPassword(student.getPassword());
        this.setRole(student.getRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(this.getRole().toString().toUpperCase()));
        return authorities;
    }

    @Override
    public String getUsername() {
        return getEmail();
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
