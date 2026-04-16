package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BankAccountDTO {
    private String accountId;
    private String accountType;
    private String status;
    private String nickname;
    private int branchId;
}
