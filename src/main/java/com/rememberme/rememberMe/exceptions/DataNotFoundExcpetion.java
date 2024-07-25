package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExceptionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

public class DataNotFoundExcpetion extends RememberMeExceptions {
    private final String detail;

    public DataNotFoundExcpetion(String detail) {
        this.detail = detail;
    }

    @Override
    public ResponseEntity<RuntimeExceptionPresenter> toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("DATA NOT FOUND");
        pb.setDetail(detail);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RuntimeExceptionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        ));
    }
}
