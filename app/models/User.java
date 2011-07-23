package models;

import play.*;
import play.db.jpa.*;

import javax.persistence.*;
import java.util.*;

@Entity
public class User extends Model {
    
	public String username;
	public String fullname;
	
	public User(String username, String fullname){
		
		super();
		this.username = username;
		this.fullname = fullname;
	}
}