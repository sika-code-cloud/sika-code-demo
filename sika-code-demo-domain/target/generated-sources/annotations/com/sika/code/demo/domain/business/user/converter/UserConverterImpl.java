package com.sika.code.demo.domain.business.user.converter;

import com.sika.code.demo.infrastructure.business.user.pojo.dto.UserDTO;
import com.sika.code.demo.infrastructure.db.business.user.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-30T13:43:57+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class UserConverterImpl implements UserConverter {

    @Override
    public UserPO convertToPo(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserPO userPO = new UserPO();

        userPO.setCreateDate( dto.getCreateDate() );
        userPO.setUpdateDate( dto.getUpdateDate() );
        userPO.setVersion( dto.getVersion() );
        userPO.setAvailable( dto.getAvailable() );
        userPO.setIsDeleted( dto.getIsDeleted() );
        userPO.setRemark( dto.getRemark() );
        userPO.setId( dto.getId() );
        userPO.setCreateBy( dto.getCreateBy() );
        userPO.setUpdateBy( dto.getUpdateBy() );
        userPO.setUsername( dto.getUsername() );
        userPO.setPassword( dto.getPassword() );
        userPO.setOauthPwd( dto.getOauthPwd() );
        userPO.setNickname( dto.getNickname() );
        userPO.setSex( dto.getSex() );
        userPO.setPhone( dto.getPhone() );
        userPO.setEmail( dto.getEmail() );
        userPO.setAvatar( dto.getAvatar() );
        userPO.setProvinceCode( dto.getProvinceCode() );
        userPO.setToken( dto.getToken() );
        userPO.setType( dto.getType() );
        userPO.setCityCode( dto.getCityCode() );
        userPO.setCountyCode( dto.getCountyCode() );
        userPO.setAddress( dto.getAddress() );
        userPO.setDeleted( dto.getDeleted() );

        return userPO;
    }

    @Override
    public UserDTO convertToDto(UserPO po) {
        if ( po == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( po.getId() );
        userDTO.setCreateDate( po.getCreateDate() );
        userDTO.setUpdateDate( po.getUpdateDate() );
        userDTO.setVersion( po.getVersion() );
        userDTO.setAvailable( po.getAvailable() );
        userDTO.setIsDeleted( po.getIsDeleted() );
        userDTO.setRemark( po.getRemark() );
        userDTO.setCreateBy( po.getCreateBy() );
        userDTO.setUpdateBy( po.getUpdateBy() );
        userDTO.setUsername( po.getUsername() );
        userDTO.setPassword( po.getPassword() );
        userDTO.setOauthPwd( po.getOauthPwd() );
        userDTO.setNickname( po.getNickname() );
        userDTO.setSex( po.getSex() );
        userDTO.setPhone( po.getPhone() );
        userDTO.setEmail( po.getEmail() );
        userDTO.setAvatar( po.getAvatar() );
        userDTO.setProvinceCode( po.getProvinceCode() );
        userDTO.setToken( po.getToken() );
        userDTO.setType( po.getType() );
        userDTO.setCityCode( po.getCityCode() );
        userDTO.setCountyCode( po.getCountyCode() );
        userDTO.setAddress( po.getAddress() );
        userDTO.setDeleted( po.getDeleted() );

        return userDTO;
    }

    @Override
    public List<UserPO> convertToPos(List<UserDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<UserPO> list = new ArrayList<UserPO>( dtos.size() );
        for ( UserDTO userDTO : dtos ) {
            list.add( convertToPo( userDTO ) );
        }

        return list;
    }

    @Override
    public List<UserDTO> convertToDtos(List<UserPO> pos) {
        if ( pos == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( pos.size() );
        for ( UserPO userPO : pos ) {
            list.add( convertToDto( userPO ) );
        }

        return list;
    }
}
