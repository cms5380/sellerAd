package com.charminseok.product.dto;

import lombok.Data;

@Data
public class Paging {
    private final Long start;
    private final Integer pageSize;
}
