package es.udc.siteapp.model;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AuditModel {
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
