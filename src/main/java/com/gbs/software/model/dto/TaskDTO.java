package com.gbs.software.model.dto;

import java.io.Serializable;

import com.gbs.software.model.entities.Task;
import com.gbs.software.model.entities.User;
import com.gbs.software.utils.MapperUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO implements Serializable {

    private Long id;
    private String name;
    private String descricao;
    private String status;
    private User user;

    public Task toEntity(){
        return MapperUtil.converte(this, Task.class);
    }


}
