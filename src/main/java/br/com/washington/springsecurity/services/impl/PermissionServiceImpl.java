package br.com.washington.springsecurity.services.impl;

import br.com.washington.springsecurity.entities.Permissions;
import br.com.washington.springsecurity.repositoy.PermissionsRepository;
import br.com.washington.springsecurity.services.PermissionService;
import br.com.washington.springsecurity.strategy.MakePermissionStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionsRepository permissionsRepository;
    private final MakePermissionStrategy permissionStrategy;

    @Override
    public Set<Permissions> setPermissions(String role) {
        Enum<?>[] values = permissionStrategy.get(role);
        Set<String> permissionNames = Arrays.stream(values)
                .map(Enum::name)
                .collect(Collectors.toSet());
        return permissionsRepository.findByNameIn(permissionNames);
    }

}
