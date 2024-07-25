package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExcpetionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class DataNotFoundExcpetion extends RememberMeExceptions {
    private final String detail;

    public DataNotFoundExcpetion(String detail) {
        this.detail = detail;
    }

    @Override
    public RuntimeExcpetionPresenter toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        pb.setTitle("DATA NOT FOUND");
        pb.setDetail(detail);

        return new RuntimeExcpetionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        );
    }
}
