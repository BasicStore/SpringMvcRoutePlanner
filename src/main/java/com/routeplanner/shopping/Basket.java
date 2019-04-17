package com.routeplanner.shopping;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;



@Entity
@Table(name="basket")
public class Basket extends DataModel {

	//private Set<AbstractItem> tickets;
	
	@OneToOne
	private User user;	
	
//	@OneToOne(mappedBy = "post", cascade = CascadeType.ALL,
//            fetch = FetchType.LAZY, optional = false)
	
	
//	@OneToOne(mappedBy = "course")
//	@JoinColumn(name = "student_id", nullable = true)
//	private Student student;
	
	
	public Basket(User user) {
		this.user = user;
	}
	
//	public Basket(User user, Set<AbstractItem> tickets) {
//		this(user);
//		this.tickets = tickets;
//	}
//
//
//	public Set<AbstractItem> getTickets() {
//		return tickets;
//	}
//
//
//	public void setTickets(Set<AbstractItem> tickets) {
//		this.tickets = tickets;
//	}

	
}

/*
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="role_id")
private int roleId;

@Column(name="role")
private String role; */