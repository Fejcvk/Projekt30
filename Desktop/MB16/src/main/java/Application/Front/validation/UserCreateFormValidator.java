package Application.Front.validation;

import Application.Front.CreateUserForm;
import Application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CreateUserFormValidator implements Validator
{
    private final UserService userService;

    @Autowired
    public CreateUserFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(CreateUserForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateUserForm form = (CreateUserForm) o;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validateEmail(Errors errors, CreateUserForm form) {
        if(userService.getUserByEmail(form.getEmail()).isPresent())
        {
            errors.rejectValue("email.exist", "User with this name already exist");
        }
    }

    private void validatePasswords(Errors errors, CreateUserForm form) {
        if(!form.getPassword().equals(form.getPasswordRepeated()))
        {
            errors.reject("password.no_match", "Given password doesn't match");
        }
    }
}
