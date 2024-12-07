package ru.t1.limit.mapper;

import org.mapstruct.Mapper;
import ru.t1.limit.model.Limit;
import t1.dto.module.dto.LimitDto;

/**
 * @author YKozlova
 */
@Mapper(componentModel = "spring")
public interface LimitMapper {

    LimitDto toLimitDto(Limit limit);
}
