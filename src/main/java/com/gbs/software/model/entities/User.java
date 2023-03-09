package com.gbs.software.model.entities;

import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.utils.MapperUtil;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Entity
@Table(name = "tb_user", schema = "gbs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(length = 1000)
    private String biografia;
    private String github;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return getId() != null && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public UserDTO toDTO(){
        return MapperUtil.converte(this, UserDTO.class);
    }

    public List<UserDTO> toDTOList(List<User> list) {
        return list.stream().map(User::toDTO).collect(Collectors.toList());
    }

}
