package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExcpetionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class RememberMeExceptions extends RuntimeException {
    public RuntimeExcpetionPresenter toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("REMEMBERME INTERNAL SERVER ERROR");

        return new RuntimeExcpetionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        );
    }
}
