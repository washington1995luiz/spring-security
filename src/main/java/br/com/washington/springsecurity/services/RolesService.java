package br.com.washington.springsecurity.services;

import br.com.washington.springsecurity.entities.Roles;

import java.util.Set;

public interface RolesService {
    Set<Roles> setRoles(String roleName);
}
