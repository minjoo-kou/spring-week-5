package com.sparta.week05.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderItemRequestDto {
    private final Long id;
    private final int quantity;
}
