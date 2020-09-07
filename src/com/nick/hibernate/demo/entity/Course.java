package com.nick.hibernate.demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "course")
@NoArgsConstructor
@Data
public class Course {

	public Course(String title) {
		super();
		this.title = title;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,
			CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;

	@ManyToMany(fetch = FetchType.LAZY, 
			cascade = {CascadeType.DETACH, CascadeType.MERGE, 
					CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "course_student", 
	joinColumns = @JoinColumn(name="course_id"), 
	inverseJoinColumns  = @JoinColumn(name="student_id"))
	private List<Student> students;

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

	public void addReview(Review review) {
		if(this.reviews == null) {
			this.reviews = new ArrayList<>();
		}		
		this.reviews.add(review);
	}
	
	public void addStudent(Student student) {
		if(this.students == null) {
			this.students = new ArrayList<>();
		}
		this.students.add(student);
	}
}
