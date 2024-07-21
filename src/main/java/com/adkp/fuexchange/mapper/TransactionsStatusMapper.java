package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.TransactionsStatusDTO;
import com.adkp.fuexchange.pojo.Transactions;
import com.adkp.fuexchange.pojo.TransactionsStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {Transactions.class}
)
public interface TransactionsStatusMapper {
    @Mapping(source = "transactionsStatusId", target = "transactionsStatusId")
    @Mapping(source = "transactionsStatusName", target = "transactionsStatusName")
    @Mapping(source = "transactionId", target = "transactionId")
    TransactionsStatusDTO toTransactionsStatusDTO(TransactionsStatus transactionsStatus);

    List<TransactionsStatusDTO> toTransactionsStatusDTOList(List<TransactionsStatus> transactionsStatus);

}
