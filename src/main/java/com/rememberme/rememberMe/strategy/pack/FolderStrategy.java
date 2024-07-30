package com.rememberme.rememberMe.strategy.pack;
import com.rememberme.rememberMe.exceptions.DataBadRequestExcpetion;
import com.rememberme.rememberMe.repositories.IFolderRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

public class FolderStrategy {


    @Service
    public static class FolderValidations implements FolderStrategyInterface {

        private final IFolderRepository folderRepository;

        public FolderValidations(IFolderRepository folderRepository) {
            this.folderRepository = folderRepository;
        }

        @Override
        public void validateTitle(String title) {
            if (title.length() > 30 || title.length() < 3) throw new DataBadRequestExcpetion(
                    "Title must be between 3 and 30 characters"
            );
        }

        @Override
        public void validateSameTitle(String title, UUID userId) {
            var userTitles = this.folderRepository.findByUserId(userId);
            if (userTitles.stream().anyMatch(response -> response.getTitle().equals(title))) throw new DataBadRequestExcpetion(
                    "FOLDER ALREADY EXISTS WITH THIS TITLE"
            );
        }
    }

}
