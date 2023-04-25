package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public record StatementDto(
        String statementDate,
        String path
) implements Serializable {
}
