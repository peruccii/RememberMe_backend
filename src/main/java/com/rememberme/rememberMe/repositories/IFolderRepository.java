package com.rememberme.rememberMe.repositories;

import com.rememberme.rememberMe.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface IFolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> findByUserId(UUID userId);
}
