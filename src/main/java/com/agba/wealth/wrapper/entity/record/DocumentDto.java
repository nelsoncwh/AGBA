package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public record DocumentDto(
        String name,
//        String nameZHTW,
//        String nameZHCN,
        String docPath
) implements Serializable {
}
