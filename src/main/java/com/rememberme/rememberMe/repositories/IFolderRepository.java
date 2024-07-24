package com.rememberme.rememberMe.repositories;

import com.rememberme.rememberMe.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFolderRepository extends JpaRepository<Folder, Long> {
}
