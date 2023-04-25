package com.agba.wealth.wrapper.entity.record;

import java.io.Serializable;

public record ConfirmationNoteDto(
        String issueDate,
        String path,
        String confirmationType,
        String confirmationTypeName
) implements Serializable {
}
