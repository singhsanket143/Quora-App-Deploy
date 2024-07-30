package org.example.quoraappclone.controllers;

import org.example.quoraappclone.dtos.UserDTO;
import org.example.quoraappclone.models.Question;
import org.example.quoraappclone.models.User;
import org.example.quoraappclone.services.UserFeedService;
import org.example.quoraappclone.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;

    private UserFeedService userFeedService;

    public UserController(UserService userService, UserFeedService userFeedService) {
        this.userService = userService;
        this.userFeedService = userFeedService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public User createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/followTag/{tagId}")
    public ResponseEntity<Void> followTag(@PathVariable Long userId, @PathVariable Long tagId) {
        userService.followTag(userId, tagId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/feed")
    public ResponseEntity<List<Question>> getUserFeed(@PathVariable Long userId, @RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(userFeedService.getUserFeed(userId, page, size));
    }
}
