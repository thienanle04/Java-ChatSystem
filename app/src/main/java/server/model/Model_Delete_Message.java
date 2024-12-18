package server.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Model_Delete_Message {
    private int ID;
    private int userID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Model_Delete_Message() {}

    // Annotate the parameterized constructor
    @JsonCreator
    public Model_Delete_Message(@JsonProperty("ID") int ID, @JsonProperty("userID") int userID) {
        this.ID = ID;
        this.userID = userID;
    }
}
