package com.agba.wealth.wrapper.entity.response;

import com.agba.wealth.wrapper.entity.record.AccountStatementDocumentDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AccountStatementRes {
    List<AccountStatementDocumentDto> documentList;
    String returnCode;
    String returnMsg;
    String processTime;
}