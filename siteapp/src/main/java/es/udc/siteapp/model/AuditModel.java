package es.udc.siteapp.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AuditModel {
	private Date createdAt;
	private Date updatedAt;
}
