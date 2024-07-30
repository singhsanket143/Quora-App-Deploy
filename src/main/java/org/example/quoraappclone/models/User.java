package org.example.quoraappclone.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.Set;

@Data
@Entity // This annotation is used to specify that the class is an entity class.
public class User extends BaseModel{

    private String username;
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_tags",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> followedTags;

}

// Question has many answers
// answer belongs to one question
// answer has many commeents
// comment can also have more comments
// a comment belongs to an answer or another comment