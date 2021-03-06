package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.UserDtoResp;
import ru.veqveq.conference.exceptions.ResourceNotFoundException;
import ru.veqveq.conference.models.Role;
import ru.veqveq.conference.models.User;
import ru.veqveq.conference.repositories.UserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public List<UserDtoResp> findAll() {
        return userRepository.findAll()
                .stream()
                .map(UserDtoResp::new)
                .collect(Collectors.toList());
    }

    public List<UserDtoResp> findAllByRole(String role) {
        return userRepository.findAllByRole_Role(role)
                .stream()
                .map(UserDtoResp::new)
                .collect(Collectors.toList());
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public void saveOrUpdate(User user) {
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByLogin(username).orElseThrow(() -> new ResourceNotFoundException("User by id: " + username + " not exist"));
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), mapUserRolesToGrantedAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapUserRolesToGrantedAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.getRole()));
    }

    public void remove(Long userId) {
        userRepository.deleteById(userId);
    }
}
