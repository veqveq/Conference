package ru.veqveq.conference.services;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.UserDto;
import ru.veqveq.conference.models.Role;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("User by id" + id + "not found")
                );
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    private Collection<? extends GrantedAuthority> mapUserRolesToGrantedAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByLogin(username);
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapUserRolesToGrantedAuthorities(user.getRoles()));
    }
}
