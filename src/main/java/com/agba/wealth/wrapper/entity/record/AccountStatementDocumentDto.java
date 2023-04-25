package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;
import java.util.List;

public record AccountStatementDocumentDto(
        String accountId,
        List<String> statement,
        List<String> confirmationNote
) implements Serializable {
}
