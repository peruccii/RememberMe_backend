package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExceptionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

public class DataAlreadyExistsException extends RememberMeExceptions {
    private final String detail;

    public DataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public ResponseEntity<RuntimeExceptionPresenter> toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("DATA ALREADY EXISTS");
        pb.setDetail(detail);

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new RuntimeExceptionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        ));
    }
}
