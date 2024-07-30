package com.rememberme.rememberMe.strategy.pack;

import java.util.UUID;

public interface FolderStrategyInterface {
    void validateTitle(String title);

    void validateSameTitle(String title, UUID userId);
}
