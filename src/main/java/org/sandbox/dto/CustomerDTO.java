package org.sandbox.dto;

import lombok.extern.jackson.Jacksonized;

@Jacksonized
public record CustomerDTO (
    Long id,
    String name,
    String email,
    String phone,
    String document,
    String address,
    Long age
) {}
