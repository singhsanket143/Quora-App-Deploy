package org.example.quoraappclone.services;

import org.example.quoraappclone.models.Question;
import org.example.quoraappclone.models.Tag;
import org.example.quoraappclone.models.User;
import org.example.quoraappclone.repositories.QuestionRepository;
import org.example.quoraappclone.repositories.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFeedService {

    private UserRepository userRepository;

    private QuestionRepository questionRepository;

    public UserFeedService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;

    }

    public List<Question> getUserFeed(Long userId, int page, int size) {

        User user = userRepository.findById((userId)).orElseThrow(() -> new RuntimeException("User not found"));

        Set<Long> tagIds = user.getFollowedTags().stream().map(Tag::getId).collect(Collectors.toSet());

        return questionRepository.findQuestionsByTags(tagIds, PageRequest.of(page, size)).getContent();
    }
}
