package com.rafaelswr.springsecurityindeep.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors

) {}
