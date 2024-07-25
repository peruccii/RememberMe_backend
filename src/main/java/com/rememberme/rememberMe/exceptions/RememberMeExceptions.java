package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExceptionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

public class RememberMeExceptions extends RuntimeException {
    public ResponseEntity<RuntimeExceptionPresenter> toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);

        pb.setTitle("REMEMBERME INTERNAL SERVER ERROR");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RuntimeExceptionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        ));
    }
}
