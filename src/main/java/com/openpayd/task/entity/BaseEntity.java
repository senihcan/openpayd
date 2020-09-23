package com.openpayd.task.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class BaseEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8286473854288978827L;
	
    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updateDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy = "System";

    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy = "System";

    @JsonIgnore
    private LocalDateTime endDate;

}
