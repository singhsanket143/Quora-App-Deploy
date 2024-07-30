package org.example.quoraappclone.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass // This annotation is used to specify that the class is an entity class that is a superclass for other entity classes.
@Data
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // This annotation is used to specify the primary key generation strategy to be used for the entity.
    private Long id;
}
