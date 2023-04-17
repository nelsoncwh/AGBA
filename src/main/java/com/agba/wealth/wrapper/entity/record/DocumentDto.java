package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public record DocumentDto(
        String nameEN,
        String nameZHTW,
        String nameZHCN,
        String docPath
) implements Serializable {
}
