package com.rememberme.rememberMe.exceptions;

import com.rememberme.rememberMe.presenters.RuntimeExcpetionPresenter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.util.List;

public class DataBadRequestExcpetion extends RememberMeExceptions {
    private final List<String> detail;

    public DataBadRequestExcpetion(List<String> detail) {
        this.detail = detail;
    }

    @Override
    public RuntimeExcpetionPresenter toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);

        pb.setTitle("BAD REQUEST DATA");
        pb.setDetail(String.valueOf(detail));

        return new RuntimeExcpetionPresenter(
                pb.getTitle(),
                pb.getStatus(),
                pb.getDetail(),
                pb.getInstance()
        );
    }
}
