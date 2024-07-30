package org.example.quoraappclone.services;

import org.example.quoraappclone.dtos.UserDTO;
import org.example.quoraappclone.models.Tag;
import org.example.quoraappclone.models.User;
import org.example.quoraappclone.repositories.TagRepository;
import org.example.quoraappclone.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private TagRepository tagRepository;

    public UserService(UserRepository userRepository, TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void followTag(Long userId, Long tagId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Tag tag = tagRepository.findById(tagId).orElseThrow(() -> new RuntimeException("Tag not found"));
        user.getFollowedTags().add(tag);
        userRepository.save(user);
    }
}
