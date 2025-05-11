package api;

public class Student {

	String name;
	String rollno;
	Character gender;
	String[] courses;
	public String getName() {
		return name;
	}
	public String getRollno() {
		return rollno;
	}
	public Character getGender() {
		return gender;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRollno(String rollno) {
		this.rollno = rollno;
	}
	public void setGender(Character gender) {
		this.gender = gender;
	}
	public String[] getCourses() {
		return courses;
	}
	public void setCourses(String[] course) {
		this.courses = course;
	}
	
	
	
}
