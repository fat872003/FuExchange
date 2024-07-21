package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.TransactionsDTO;
import com.adkp.fuexchange.pojo.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.text.DecimalFormat;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {TransactionsStatusMapper.class}
)
public interface TransactionsMapper {

    @Mapping(source = "transactionsId", target = "transactionsId")
    @Mapping(source = "paymentId", target = "payment")
    @Mapping(source = "transactionsStatusId", target = "transactionsStatus")
    @Mapping(source = "totalPrice", target = "totalPrice", qualifiedByName = "formatPrice")
    @Mapping(source = "createTime", target = "createTime")
    @Mapping(source = "completeTime", target = "completeTime")
    TransactionsDTO toTransactionsDTO(Transactions transactions);

    List<TransactionsDTO> toTransactionsDTOList(List<Transactions> transactionsList);

    @Named("formatPrice")
    default String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#.000");
        return df.format(price);
    }

}
