package com.adkp.fuexchange.mapper;

import com.adkp.fuexchange.dto.RoleDTO;
import com.adkp.fuexchange.pojo.Roles;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    @Mapping(source = "roleId", target = "roleId")
    @Mapping(source = "roleName", target = "roleName")
    RoleDTO toRoleDTO(Roles roles);

    @InheritInverseConfiguration(name = "toRoleDTO")
    Roles toRoles(RoleDTO roleDTO);
}
