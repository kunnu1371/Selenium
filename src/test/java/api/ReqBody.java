package api;

public class ReqBody {

	String name;
	String job;
	String id;
	String skills[];

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public String getId() {
		return id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String[] getSkills() {
		return skills;
	}

	public void setSkills(String[] arr) {
		this.skills = arr;
	}

}
