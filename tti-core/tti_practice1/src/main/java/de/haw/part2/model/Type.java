package de.haw.part2.model;

public enum Type {
	
	SOUND("sound", "Musik"),
	BOOK("book", "Buch"),
	PICTURE("picture", "Bild"),
	SOFTWARE("software", "Software"),
	UNKNOWN("unknown", "Unbekannt");
	
	private String english;
	
	private String german;
	
	private Type(String english, String german){
		this.english = english;
		this.german = german;
	}
	
	public static Type getType(String type){
		for (Type typeEn : Type.values()) {
			if(type.equalsIgnoreCase(typeEn.english)){
				return typeEn;
			}else if(type.equalsIgnoreCase(typeEn.german)){
				return typeEn;
			}
		}
		return Type.UNKNOWN;
	}

	public String getEnglish() {
		return english;
	}

	public String getGerman() {
		return german;
	}

}
