package com.boot.validator;

import com.boot.entity.User;
import com.boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring

/**
 * Created by Chaklader on Oct, 2017
 */
@Component
public class UserFormValidator implements Validator {

    //    @Qualifier("emailValidator")
    @Autowired
    EmailValidator emailValidator;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.updateUser.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.updateUser.email");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "NotEmpty.updateUser.address");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.updateUser.password");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.updateUser.confirmPassword");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sex", "NotEmpty.updateUser.sex");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "country", "NotEmpty.updateUser.country");

//        if (!emailValidator.valid(user.getEmail())) {
//            errors.rejectValue("email", "Pattern.updateUser.email");
//        }

//        if (user.getNumber() == null || user.getNumber() <= 0) {
//            errors.rejectValue("number", "NotEmpty.updateUser.number");
//        }
//
//        if (user.getCountry().equalsIgnoreCase("none")) {
//            errors.rejectValue("country", "NotEmpty.updateUser.country");
//        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.updateUser.confirmPassword");
        }

        if (user.getFramework() == null || user.getFramework().size() < 2) {
            errors.rejectValue("framework", "Valid.updateUser.framework");
        }

//        if (user.getSkill() == null || user.getSkill().size() < 3) {
//            errors.rejectValue("skill", "Valid.updateUser.skill");
//        }
    }
}