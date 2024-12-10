package user.event;

import user.model.Model_Login;
import user.model.Model_Register;

public interface EventLogin {
    public void goRegister();

    public void goLogin();

    public void register(Model_Register data, EventMessage message);
    
    public void login(Model_Login data);

    public void goResetPassword();

    public void goBackResetPassword();

    public void goVerify();
}
