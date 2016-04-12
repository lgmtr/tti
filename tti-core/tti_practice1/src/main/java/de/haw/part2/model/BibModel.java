package de.haw.part2.model;

import java.time.LocalDate;
import java.util.UUID;

public class BibModel {

	private UUID id;

	private String title;

	private String subject;

	private LocalDate date;

	private String creator;

	private Type type;

	private String description;

	private String relation;

	public BibModel(UUID id, String title, String subject, LocalDate date, String creator, Type type, String description, String relation) {
		this.id = id;
		this.title = title;
		this.subject = subject;
		this.date = date;
		this.creator = creator;
		this.type = type;
		this.description = description;
		this.relation = relation;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

}
