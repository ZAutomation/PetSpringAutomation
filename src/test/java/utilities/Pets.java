package utilities;

/* Pets attributes which will be used to create object , all as String as we will use it to capture all info*/
public class Pets {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Pets [petName=" + petName + ", owner=" + owner + ", type=" + type + ", birthDate=" + birthDate
				+ ", visitDesc=" + visitDesc + "]";
	}
	private String petName;
	private String owner;
	private String type;
	private String birthDate;
	private String visitDesc;
	/**
	 * @param petName
	 * @param owner
	 * @param type
	 * @param birthDate
	 * @param visitDesc
	 */
	public Pets(String petName, String owner, String type, String birthDate, String visitDesc) {
		this.petName = petName;
		this.owner = owner;
		this.type = type;
		this.birthDate = birthDate;
		this.visitDesc = visitDesc;
	}
	/**
	 * @return the petName
	 */
	public String getPetName() {
		return petName;
	}
	/**
	 * @return the owner
	 */
	public String getOwner() {
		return owner;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @return the birthDate
	 */
	public String getBirthDate() {
		return birthDate;
	}
	/**
	 * @return the visitDesc
	 */
	public String getVisitDesc() {
		return visitDesc;
	}
	/**
	 * @param petName the petName to set
	 */
	public void setPetName(String petName) {
		this.petName = petName;
	}
	/**
	 * @param owner the owner to set
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @param birthDate the birthDate to set
	 */
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * @param visitDesc the visitDesc to set
	 */
	public void setVisitDesc(String visitDesc) {
		this.visitDesc = visitDesc;
	}
	
	public void printInfo() {
		System.out.println(toString());
	}
}
