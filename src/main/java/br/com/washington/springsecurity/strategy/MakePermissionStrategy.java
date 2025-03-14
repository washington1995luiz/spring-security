package br.com.washington.springsecurity.strategy;

import br.com.washington.springsecurity.enums.AdminPermissionName;
import br.com.washington.springsecurity.enums.CustomerPermissionName;
import br.com.washington.springsecurity.enums.RoleName;
import br.com.washington.springsecurity.enums.VendorPermissionName;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Component
public class MakePermissionStrategy {
    private final Map<String, Enum<?>[]> permissionStrategy =
            Map.of(
                    RoleName.ADMIN.name(), AdminPermissionName.values(),
                    RoleName.CUSTOMER.name(), CustomerPermissionName.values(),
                    RoleName.VENDOR.name(), VendorPermissionName.values()
            );

    public Enum<?>[] get(String role) {
        return Optional.ofNullable(permissionStrategy.get(role.toUpperCase(Locale.ROOT))).orElseThrow(RuntimeException::new);
    }
}
