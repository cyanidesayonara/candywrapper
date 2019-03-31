package com.candywrapper.controller;

import com.candywrapper.model.Role;
import com.candywrapper.service.RoleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RoleAPIController {

    public static final Logger logger = LoggerFactory.getLogger(RoleAPIController.class);

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles/")
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roles = roleService.findAll();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        logger.info("Fetching role with id {}", id);
        Role role = roleService.findById(id);
        if (role == null) {
            logger.error("Role with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Role>(role, HttpStatus.OK);
    }

    @PostMapping("/roles/")
    public ResponseEntity<?> save(@RequestBody @Valid Role role) {
        logger.info("Saving Role : {}", role);

        if (roleService.existsByName(role.getName())) {
            logger.error("A Role with name {} already exists", role.getName());
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Role createdRole = roleService.save(role);
        return new ResponseEntity<Role>(createdRole, HttpStatus.OK);
    }

    @PutMapping("/roles/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Role role) {
        logger.info("Updating Role with id {}", id);

        Role currentRole = roleService.findById(id);
        if (currentRole == null) {
            logger.error("Role with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentRole.setName(role.getName());

        Role updatedRole = roleService.update(role);
        return new ResponseEntity<Role>(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") String id) {
        logger.info("Fetching and deleting Role with id {}", id);

        if (roleService.findById(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        roleService.deleteById(id);
        return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/roles/")
    public ResponseEntity<?> deleteAll() {
        logger.info("Deleting all Roles");

        roleService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}