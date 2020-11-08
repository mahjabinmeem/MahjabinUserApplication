package mahjabin.userapp;

public class Child {
    private String childFirstName;
    private String childLastName;
    private String parentFirstName;
    private String parentLastName;

    @Override
    public String toString() {
        return "Child{" +
                "Child First Name='" + childFirstName + '\'' +
                ", Child Last Name='" + childLastName + '\'' +
                ", Parent First Name'" + parentFirstName + '\'' +
                ", Parent Last Name '" + parentLastName + '\'' +
                '}';
    }

    String getParentFirstName() {
        return parentFirstName;
    }

    void setParentFirstName(String parentFirstName) {
        this.parentFirstName = parentFirstName;
    }

    String getParentLastName() {
        return parentLastName;
    }

    void setParentLastName(String parentLastName) {
        this.parentLastName = parentLastName;
    }

    String getChildFirstName() {

        return childFirstName;
    }

    void setChildFirstName(String childFirstName) {

        this.childFirstName = childFirstName;
    }

    String getChildLastName() {

        return childLastName;
    }

    void setChildLastName(String childLastName) {

        this.childLastName = childLastName;
    }
}
