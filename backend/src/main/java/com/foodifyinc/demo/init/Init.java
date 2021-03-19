package com.foodifyinc.demo.init;

import com.foodifyinc.demo.domain.*;
import com.foodifyinc.demo.repository.PermissionRepository;
import com.foodifyinc.demo.repository.RoleRepository;
import com.foodifyinc.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@RequiredArgsConstructor
@Component
public class Init {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Value("${db.initialize}")
    private boolean initialize;

    @PostConstruct
    public void init(){
        if(initialize){
            Set<Permission> userPermissions = loadUserPermissions();
            Set<Permission> adminPermissions = loadAdminPermissions();
            Role userRole = loadUserRole(userPermissions);
            Role adminRole = loadAdminRole(adminPermissions);
            loadTestUsers(userRole, adminRole);
        }
    }

    private Set<Permission> loadUserPermissions(){
        Set<Permission> permissions = new HashSet<>();
        List<PermissionName> userPermissionNames = new ArrayList<>();
        userPermissionNames.add(PermissionName.USER);

        for (PermissionName permissionName : PermissionName.values()){
            if(userPermissionNames.contains(permissionName)){
                permissions.add(new Permission(permissionName));
            }
        }
        for(Permission permission : permissions){
            permissionRepository.save(permission);
        }
        return permissions;
    }

    private Set<Permission> loadAdminPermissions(){
        Set<Permission> permissions = new HashSet<>();
        List<PermissionName> userPermissions = new ArrayList<>();
        userPermissions.add(PermissionName.ADMINISTRATOR);
        for (PermissionName permissionName : PermissionName.values()){
            if(userPermissions.contains(permissionName)){
                permissions.add(new Permission(permissionName));
            }
        }
        for(Permission permission : permissions){
            permissionRepository.save(permission);
        }
        return permissions;
    }

    private Role loadUserRole(Set<Permission> permissions){
        Role userRole = new Role(RoleName.USER, permissions);
        return roleRepository.save(userRole);
    }

    private Role loadAdminRole(Set<Permission> permissions){
        Role adminRole = new Role(RoleName.ADMINISTRATOR, permissions);
        return roleRepository.save(adminRole);
    }

    private void loadTestUsers(Role userRole, Role adminRole){
        User userUser = new User("test", bCryptPasswordEncoder.encode("test"), Collections.singleton(userRole));
        User adminUser = new User("admin", bCryptPasswordEncoder.encode("admin"), Collections.singleton(adminRole));

        userRepository.save(userUser);
        userRepository.save(adminUser);
    }

}
