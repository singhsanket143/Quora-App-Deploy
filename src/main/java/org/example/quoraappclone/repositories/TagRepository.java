package org.example.quoraappclone.repositories;

import org.example.quoraappclone.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
