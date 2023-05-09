package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public record TradablePrdListDto(
        String productType,
        String status
) implements Serializable {

}
