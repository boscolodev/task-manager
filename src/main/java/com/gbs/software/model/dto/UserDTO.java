package com.gbs.software.model.dto;

import com.gbs.software.model.entities.User;
import com.gbs.software.utils.MapperUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String senha;
    private String biografia;
    private String github;

    public User toEntity(){
        return MapperUtil.converte(this, User.class);
    }


}
