package com.aruw.greetings.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Email
    @NotBlank
    private String address;

    @NotBlank
    private String subject;

    @NotBlank
    private String content;

    @NotNull
    private Boolean isHtmlContent;

}