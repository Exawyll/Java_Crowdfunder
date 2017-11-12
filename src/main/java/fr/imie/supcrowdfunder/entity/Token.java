/**
 * 
 */
package fr.imie.supcrowdfunder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jerome
 *
 */
@Entity
@XmlRootElement(name = "token")
public class Token {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String content;

	public Token() {
	}

	public Token(String string) {
		content = string;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
