package com.charminseok.settlement.openfeign.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorForm {
    private String code;
    private String message;
}
