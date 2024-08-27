package com.example.nursinghome.repository.httpclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Quang Duong
 * @summary java 21
 * @since 2024/08/27
 */
@FeignClient(name = "mail-client", url = "${app.url.mailclient}")
public interface MailClient {
    @GetMapping(value = "/send")
    void sendEmail();
}
