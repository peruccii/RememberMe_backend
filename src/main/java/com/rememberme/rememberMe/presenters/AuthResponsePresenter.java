package com.rememberme.rememberMe.presenters;

public record AuthResponsePresenter(String accessToken, Long expiresIn) {
}
