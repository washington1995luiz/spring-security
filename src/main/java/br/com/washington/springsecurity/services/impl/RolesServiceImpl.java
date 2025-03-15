package br.com.washington.springsecurity.services.impl;

import br.com.washington.springsecurity.entities.Permissions;
import br.com.washington.springsecurity.entities.Roles;
import br.com.washington.springsecurity.exception.InvalidRoleValuePassedException;
import br.com.washington.springsecurity.repositoy.RolesRepository;
import br.com.washington.springsecurity.services.PermissionService;
import br.com.washington.springsecurity.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;
    private final PermissionService permissionService;

    @Override
    public Set<Roles> setRoles(String roleName) {
        Set<Permissions> permissions = permissionService.setPermissions(roleName);
        Roles role = rolesRepository.findByName(roleName)
                .orElseThrow(() -> new InvalidRoleValuePassedException(roleName));
        role.setPermissions(permissions);
        return Set.of(role);
    }

}
