package com.rememberme.rememberMe.presenters;

import org.springframework.http.ProblemDetail;

public record RuntimeExcpetionPresenter(String title, Integer status, String detail, java.net.URI instance) {
}
