package db;

//联系人实体类
public class Contact {
	private String name;
	private String sex;
	private int age;
	private String phone;
	private String email;
	
	//各属性的getter、setter方法
	public void setName(String name) {
		this.name = name;
	}	
	public String getName() {
		return name;
	}	
	public int getAge() {
		return age;
	}	
	public void setAge(int age) {
		this.age = age;
	}	
	public String getEmail() {
		return email;
	}	
	public void setEmail(String email) {
		this.email = email;
	}	
	public String getPhone() {
		return phone;
	}	
	public void setPhone(String phone) {
		this.phone = phone;
	}	
	public String getSex() {
		return sex;
	}	
	public void setSex(String sex) {
		this.sex = sex;
	}
}
