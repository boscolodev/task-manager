package com.gbs.software.model.entities;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.utils.MapperUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_user", schema = "gbs")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;
    
    @Column(length = 1000)
    private String biografia;

    @Column(nullable = true)
    private String github;
    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "task_id", referencedColumnName = "id", nullable = false)
	private List<Task> tasks;

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
