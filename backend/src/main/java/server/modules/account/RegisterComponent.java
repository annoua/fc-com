package server.modules.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import server.config.Lang;
import server.entities.ResetPasswordToken;
import server.entities.Role;
import server.entities.User;
import server.entities.VerificationToken;
import server.entities.dto.request.UserRequest;
import server.entities.dto.response.RegisterResponse;
import server.exceptions.EmailSendException;
import server.exceptions.FccExcpetion;
import server.exceptions.RegisterErrorException;
import server.exceptions.WrongFormatException;
import server.modules.authentication.Authenticator;
import server.modules.dbconnector.ResetPasswordTokenConnector;
import server.modules.dbconnector.RoleConnector;
import server.modules.dbconnector.TokenConnector;
import server.modules.dbconnector.UserConnector;
import server.modules.mailsender.MailComponent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RegisterComponent {

    private final Authenticator authenticator;
    private final UserConnector userConnector;
    private final TokenConnector tokenConnector;
    private final RoleConnector roleConnector;
    private final ResetPasswordTokenConnector resetPasswordTokenConnector;

    private final MailComponent mailComponent;

//    final private UserRepository userRepository;
//    final private RoleRepository roleRepository;
//    final private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public RegisterComponent(Authenticator authenticator, UserConnector userConnector, RoleConnector roleConnector, TokenConnector tokenConnector, ResetPasswordTokenConnector resetPasswordTokenConnector, MailComponent mailComponent) {
        this.authenticator = authenticator;
        //this.mail = mail;
        this.userConnector = userConnector;
        this.roleConnector = roleConnector;
        this.tokenConnector = tokenConnector;
        this.resetPasswordTokenConnector = resetPasswordTokenConnector;
        this.mailComponent = mailComponent;
    }

    public boolean isUserNameTaken(String name) {
        return userConnector.getUserByName(name) != null;
    }

    public boolean isUsernameLengthIncorrect(String name) {
        return (name.length() < 3 || name.length() > 12);
    }

    public boolean isUsernameIncorrect(String name) {
        Pattern pattern = Pattern.compile("\\A[a-zA-Z0-9]+([_\\-]?[a-zA-Z0-9])*\\z");
        Matcher matcher = pattern.matcher(name);
        return !matcher.matches();
    }

    public boolean isEmailTaken(String email) {
        return userConnector.getUserByEmail(email) != null;
    }

    public boolean isEmailIncorrect(String mail) {
        Pattern pattern = Pattern.compile("\\A(?=[a-z0-9@.!#$%&'*+/=?^_`{|}~-]{6,254}\\z)" +
                "(?=[a-z0-9.!#$%&'*+/=?^_`{|}~-]{1,64}@)" +
                "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*" +
                "@(?:(?=[a-z0-9-]{1,63}\\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+" +
                "(?=[a-z0-9-]{1,63}\\z)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z");
        Matcher matcher = pattern.matcher(mail);
        return !matcher.matches();
    }

    public boolean isPasswordLengthIncorrect(String password) {
        return (password.length() < 6 || password.length() > 32);
    }

    public RegisterResponse checkEntriesAndGetResponse(UserRequest userRequest) throws FccExcpetion {
        try {
            RegisterResponse registerResponse = new RegisterResponse();

            //USERNAME
            if (isUserNameTaken(userRequest.getUsername())) {
                registerResponse.setMessageUsername(Lang.UsernameIsTaken);
            } else {
                if (isUsernameIncorrect(userRequest.getUsername())) {
                    registerResponse.setMessageUsername(Lang.UsernameSymbols);
                } else {
                    if (isUsernameLengthIncorrect(userRequest.getUsername())) {
                        registerResponse.setMessageUsername(Lang.UsernameTooShort);
                    }
                }
            }

            //MAIL
            if (isEmailTaken(userRequest.getEmail())) {
                registerResponse.setMessageEmail(Lang.EmailIsTaken);
            } else {
                if (isEmailIncorrect(userRequest.getEmail())) {
                    registerResponse.setMessageEmail(Lang.EmailFormat);
                }
            }

            //PASSWORD
            if (isPasswordLengthIncorrect(userRequest.getPassword())) {
                registerResponse.setMessagePassword(Lang.PasswordTooShort);
            }

            if (!registerResponse.isOk()) {
                throw new RegisterErrorException(registerResponse);
            }

            return registerResponse;
        } catch (NullPointerException e) {
            throw new WrongFormatException();
        }
    }

    public User createNewUser(UserRequest userRequest) {
        User newUser = new User();
        newUser.insertDTOData(userRequest);
        newUser.setPassword(authenticator.encodePassword(newUser.getPassword()));
        Role role = roleConnector.findById(1);
        newUser.setRole(role);
        //TODO: DELETE DEBUG
        VerificationToken verificationToken = new VerificationToken(newUser);
        if (newUser.getUsername().equals("debugUser")) {
            verificationToken.setToken("debugging");
        }
        userConnector.save(newUser);
        tokenConnector.save(verificationToken);

        return newUser;
    }

    public void sendVerificationMail(User user) throws EmailSendException {
        VerificationToken token = tokenConnector.getTokenByUser(user);
        try {
            mailComponent.send(user.getEmail(), user.getUsername(), String.valueOf(user.getId()), token.getToken(), MailComponent.Purpose.REGISTER);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailSendException();
        }
    }

    public void sendNewPasswordMail(User user) throws EmailSendException {
        ResetPasswordToken token = resetPasswordTokenConnector.getTokenByUser(user);
        try {
            mailComponent.send(user.getEmail(), user.getUsername(), String.valueOf(user.getId()), token.getToken(), MailComponent.Purpose.RESETPASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailSendException();
        }
    }
}
