package com.agba.wrapper.entity.record;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Investor {
    String investorId;
    String accountNumber;
    String clientNumber;
    String accountType;
    String firstName;
    String lastName;
    String chineseName;
}
