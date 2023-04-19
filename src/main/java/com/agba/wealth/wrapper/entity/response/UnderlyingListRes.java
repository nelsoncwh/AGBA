package com.agba.wealth.wrapper.entity.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnderlyingListRes {
    String underlyingId;
    String underlyingName;
    String underlyingMarket;
    String underlyingType;
}
