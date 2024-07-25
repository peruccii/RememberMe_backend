package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExcpetionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DataAlreadyExistsException extends RememberMeExceptions {
    private final String detail;

    public DataAlreadyExistsException(String detail) {
        this.detail = detail;
    }

    @Override
    public RuntimeExcpetionPresenter toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);

        pb.setTitle("DATA ALREADY EXISTS");
        pb.setDetail(detail);

        return new RuntimeExcpetionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        );
    }
}
