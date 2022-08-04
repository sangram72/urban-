package com.gpch.login.service;

import com.gpch.login.model.Incident;
import com.gpch.login.model.Role;
import com.gpch.login.model.User;
import com.gpch.login.repository.IncidentRepository;
import com.gpch.login.repository.RoleRepository;
import com.gpch.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private IncidentRepository incidentRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder,IncidentRepository incidentRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.incidentRepository = incidentRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }
    public Incident saveIncident(Incident incident) {
        return incidentRepository.save(incident);
    }
    
    public List<Incident> listIncident() {
        return incidentRepository.findAll();
    }

}