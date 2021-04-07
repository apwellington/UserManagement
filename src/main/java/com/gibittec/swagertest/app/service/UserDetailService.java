package com.gibittec.swagertest.app.service;

import com.gibittec.swagertest.app.model.User;
import com.gibittec.swagertest.app.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailService(UserRepository userRepositoryl) {
        this.userRepository = userRepositoryl;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(s);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getActiv(), //activo
                user.getActiv(), //cuenta no expirada
                user.getActiv(), //credenciales no expiradas
                user.getActiv(), // cuenta no bloqueada
                builGranted(user.getRol())
        );
    }

    public List<GrantedAuthority> builGranted(Integer rol){
        String[] roles = {"LECTOR", "USUARIO", "ADMINISTRADOR"};
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (int i = 0; i < rol; i++) {
            authorityList.add(new SimpleGrantedAuthority(roles[i]));
        }
        return authorityList;
    }
}
