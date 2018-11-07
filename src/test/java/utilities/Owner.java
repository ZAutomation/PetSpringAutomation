package utilities;

/* Owner attributes which will be used to create object */
public class Owner {
	private String Name;
	private String Address;
	private String City;
	private String Telephone;
	private String pets;
	

	
	/**
	 * @param name
	 * @param address
	 * @param city
	 * @param telephone
	 * @param pets
	 */
	public Owner(String name, String address, String city, String telephone, String pets) {
		Name = name;
		Address = address;
		City = city;
		Telephone = telephone;
		this.pets = pets;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return Address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return Telephone;
	}
	/**
	 * @return the pets
	 */
	public String getPets() {
		return pets;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		Address = address;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		Telephone = telephone;
	}
	/**
	 * @param pets the pets to set
	 */
	public void setPets(String pets) {
		this.pets = pets;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Owner [Name=" + Name + ", Address=" + Address + ", City=" + City + ", Telephone=" + Telephone
				+ ", pets=" + pets + "]";
	}
	
	public void printInfo() {
		System.out.println(toString());
	}
	
	

}
