package com.br.bodysync.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.bodysync.model.User;
import com.br.bodysync.repository.UserRepository;
import com.br.bodysync.security.impl.UserDetailsImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Nenhum usuário foi encontrado com esse CPF!"));
        return new UserDetailsImpl(user.getId(), user.getCpf(), user.getEmail(), user.getFullName(), user.getPassword(),
                user.getType());
    }

}
