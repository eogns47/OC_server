package OrangeCorps.LBridge.Validator;

import OrangeCorps.LBridge.Entity.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean validateUserListLength(List<User> users,int required){
        return users.size()==required;
    }
}
