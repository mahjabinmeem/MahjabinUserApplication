package mahjabin.userapp;

public class Parent {
    private String mFirstName;
    private String mLastName;
    private String mStreet;
    private String mCity;
    private String mState;
    private String mZip;

    @Override
    public String toString() {
        return "Parent{" +
                "FirstName='" + mFirstName + '\'' +
                ", LastName='" + mLastName + '\'' +
                ", Street='" + mStreet + '\'' +
                ", City='" + mCity + '\'' +
                ", State='" + mState + '\'' +
                ", Zip='" + mZip + '\'' +
                '}';
    }

    String getmFirstName() {

        return mFirstName;
    }

    void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    String getmLastName() {

        return mLastName;
    }

    void setmLastName(String mLastName) {

        this.mLastName = mLastName;
    }

    String getmStreet() {

        return mStreet;
    }

    void setmStreet(String mStreet) {

        this.mStreet = mStreet;
    }

    String getmCity() {

        return mCity;
    }

    void setmCity(String mCity) {

        this.mCity = mCity;
    }

    String getmState() {

        return mState;
    }

    void setmState(String mState) {

        this.mState = mState;
    }

    String getmZip() {

        return mZip;
    }

    void setmZip(String mZip) {

        this.mZip = mZip;
    }
}
