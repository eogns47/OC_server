package OrangeCorps.LBridge.Service.Validator;

import OrangeCorps.LBridge.Domain.User.User;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
    public boolean validateUserListLength(List<User> users,int required){
        return users.size()==required;
    }
}
