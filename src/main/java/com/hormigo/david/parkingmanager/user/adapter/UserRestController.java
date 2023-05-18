package com.hormigo.david.parkingmanager.user.adapter;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hormigo.david.parkingmanager.core.api.UserModelAssembler;
import com.hormigo.david.parkingmanager.core.exceptions.UserDoesNotExistsException;
import com.hormigo.david.parkingmanager.core.exceptions.UserExistsException;
import com.hormigo.david.parkingmanager.user.domain.User;
import com.hormigo.david.parkingmanager.user.domain.UserDao;
import com.hormigo.david.parkingmanager.user.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserRestController {

    private final UserService userService;
    private final UserModelAssembler userModelAssembler;

    public UserRestController(UserService userService, UserModelAssembler userModelAssembler) {
        this.userService = userService;
        this.userModelAssembler = userModelAssembler;
    }

    @GetMapping("/api/users")
    public CollectionModel<EntityModel<User>> all() throws UserDoesNotExistsException {
        List<EntityModel<User>> users = null;
        users = userService.getAll().stream()
                .map(userModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users, linkTo(methodOn(UserRestController.class).all()).withSelfRel());

    }

    @GetMapping("/api/users/{id}")
    public EntityModel<User> getById(@PathVariable Long id) throws UserDoesNotExistsException {
        User user = this.userService.getUser(id).orElseThrow(UserDoesNotExistsException::new);
        return this.userModelAssembler.toModel(user);

    }

    /**
     * 
     * @param userDao
     * @return
     * @throws UserExistsException
     */
    @PostMapping("/api/users")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDao userDao) throws UserExistsException {
        {
            EntityModel<User> entityModel = this.userModelAssembler.toModel(this.userService.register(userDao));

            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);

        }

    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable long id) throws UserDoesNotExistsException {
        this.userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
