/**
 * Created by jakefroeb on 9/12/16.
 */
public class Person implements Comparable{
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String country;
    private String ipAddress;

    public Person(String id, String firstName, String lastName, String email, String country, String ipAddress){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.country = country;
        this.ipAddress = ipAddress;
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        int i;
        i = lastName.compareTo(p.lastName);
        if(i==0){
            return firstName.compareTo(p.firstName);
        }else{
            return i;
        }
    }

    @Override
    public String toString(){
        return String.format("%s %s from %s \n",firstName,lastName,country);
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
