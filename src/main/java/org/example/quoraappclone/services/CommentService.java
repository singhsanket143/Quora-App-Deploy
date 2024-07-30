package org.example.quoraappclone.services;


import org.example.quoraappclone.dtos.CommentDTO;
import org.example.quoraappclone.models.Answer;
import org.example.quoraappclone.models.Comment;
import org.example.quoraappclone.repositories.AnswerRepository;
import org.example.quoraappclone.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    private AnswerRepository answerRepository;

    public CommentService(CommentRepository commentRepository, AnswerRepository answerRepository) {
        this.commentRepository = commentRepository;
        this.answerRepository = answerRepository;
    }

    public List<Comment> getCommentsByAnswerId(Long answerId, int page, int size) {
        return commentRepository.findByAnswerId(answerId, PageRequest.of(page, size)).getContent();
    }

    public List<Comment> getRepliesByCommentId(Long commentId, int page, int size) {
        return commentRepository.findByParentCommentId(commentId, PageRequest.of(page, size)).getContent();
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());

        Optional<Answer> answer = answerRepository.findById(commentDTO.getAnswerId());
        answer.ifPresent(comment::setAnswer);

        if (commentDTO.getParentCommentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(commentDTO.getParentCommentId());
            parentComment.ifPresent(comment::setParentComment);
        }

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}