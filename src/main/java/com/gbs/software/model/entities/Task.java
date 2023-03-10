package com.gbs.software.model.entities;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.gbs.software.enums.StatusTask;
import com.gbs.software.model.dto.TaskDTO;
import com.gbs.software.utils.MapperUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_user", schema = "gbs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Task extends BaseEntity{

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String descricao;

	@Enumerated(EnumType.STRING)
    private StatusTask statusTask;
    
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
	private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Task task = (Task) o;
        return getId() != null && Objects.equals(getId(), task.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public TaskDTO toDTO(){
        return MapperUtil.converte(this, TaskDTO.class);
    }

    public List<TaskDTO> toDTOList(List<Task> list) {
        return list.stream().map(Task::toDTO).collect(Collectors.toList());
    }
}