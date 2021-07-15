package ru.veqveq.conference.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.veqveq.conference.dto.RoleDto;
import ru.veqveq.conference.models.Role;
import ru.veqveq.conference.repositories.RoleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Optional<Role> findById(Byte roleId) {
        return roleRepository.findById(roleId);
    }

    public List<RoleDto> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }

}
