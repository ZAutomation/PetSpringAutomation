package utilities;

public class Vets {

private String Name;
private String Specialties;
/**
 * @param name
 * @param specialties
 */
public Vets(String name, String specialties) {
	Name = name;
	Specialties = specialties;
}
/**
 * @return the name
 */
public String getName() {
	return Name;
}
/**
 * @return the specialties
 */
public String getSpecialties() {
	return Specialties;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	Name = name;
}
/**
 * @param specialties the specialties to set
 */
public void setSpecialties(String specialties) {
	Specialties = specialties;
}
/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "Vets [Name=" + Name + ", Specialties=" + Specialties + "]";
}

public void printInfo() {
	System.out.println(toString());
}

}
