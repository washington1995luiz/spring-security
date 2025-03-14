package br.com.washington.springsecurity.repositoy;

import br.com.washington.springsecurity.entities.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface PermissionsRepository extends JpaRepository<Permissions, UUID> {
    Optional<Permissions> findByName(String name);
    Set<Permissions> findByNameIn(Set<String> permissionNames);
}
