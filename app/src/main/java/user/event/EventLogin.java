package user.event;

import user.model.Model_Login;
import user.model.Model_Register;
import user.model.Model_Reset_Password;

public interface EventLogin {
    public void goRegister();

    public void goLogin();

    public void register(Model_Register data, EventMessage message);
    
    public void login(Model_Login data);
    
    public void forgetPassword(Model_Reset_Password newInfo, EventMessage message);

    public void goResetPassword();
}
