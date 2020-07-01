package ru.darzam.payservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import ru.darzam.payservice.dto.AccountDto;
import ru.darzam.payservice.entity.Account;

/**
 * @author zamaliev
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AccountMapper extends BaseMapper<Account, AccountDto> {

}
