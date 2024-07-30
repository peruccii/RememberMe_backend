package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExceptionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;


public class DataBadRequestExcpetion extends RememberMeExceptions {
    private final String detail;

    public DataBadRequestExcpetion(String detail) {
        this.detail = detail;
    }

    @Override
    public ResponseEntity<RuntimeExceptionPresenter> toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("BAD REQUEST DATA");
        pb.setDetail(detail);


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new RuntimeExceptionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        ));
    }
}
