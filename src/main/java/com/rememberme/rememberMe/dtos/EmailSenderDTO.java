package com.rememberme.rememberMe.dtos;

public record EmailSenderDTO(
        String ownerRef,
        String emailFrom,
        String emailTo,
        String subject,
        String text
) {
}
