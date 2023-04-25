package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;
import java.util.List;

public record AccountStatementDocumentDto(
        String accountId,
        List<StatementDto> statement,
        List<ConfirmationNoteDto> confirmationNote
) implements Serializable {
}
