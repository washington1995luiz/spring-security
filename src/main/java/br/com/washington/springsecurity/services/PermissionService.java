package br.com.washington.springsecurity.services;

import br.com.washington.springsecurity.entities.Permissions;

import java.util.Set;

public interface PermissionService {
    Set<Permissions> setPermissions(String roleName);


}
