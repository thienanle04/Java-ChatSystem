package user.event;

import user.model.Model_User_Profile;
import user.model.Model_Reset_Password;

public interface EventUpdateInfo {
    public void updateProfile(Model_User_Profile profile);
    
    public void updatePassword(Model_Reset_Password password, EventMessage message);
}
