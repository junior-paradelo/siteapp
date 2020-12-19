package es.udc.siteapp.model;

import java.sql.Timestamp;

import lombok.Data;

@Data
public abstract class AuditModel {
	private Timestamp createdAt;	
	private Timestamp updatedAt;
}
