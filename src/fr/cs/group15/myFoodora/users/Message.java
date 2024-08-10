package fr.cs.group15.myFoodora.users;

import java.time.LocalDateTime;

public class Message {
	private String content;
	private LocalDateTime date;
	
	/**
	 * 
	 * @param content
	 */
	public Message(String content) {
        this.content = content;
        this.date = LocalDateTime.now();
    }

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the date
	 */
	public LocalDateTime getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Message sent at " + date + " : " + content;
	}
	
	
}
