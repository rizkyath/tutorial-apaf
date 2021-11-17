package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.UserModel;
import apap.tutorial.pergipergi.repository.UserDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDb userDb;

    @Override
    public UserModel addUser(UserModel user) {
        String pass = encrypt(user.getPassword());
        user.setPassword(pass);
        return userDb.save(user);
    }

    @Override
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordHash = passwordEncoder.encode(password);
        return passwordHash;
    }

    @Override
    public List<UserModel> getListUser() {
        return userDb.findAll();
    }

    @Override
    public UserModel deleteById(String userId) {
        userDb.deleteById(userId);
        return userDb.getUserModelById(userId);
    }

    @Override
    public UserModel getUserByUsername(String username) {
        return userDb.getUserModelByUsername(username);
    }

    @Override
    public boolean checkPassword(String password) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean symbolFlag = false;
        if (password.length() < 8) return false;
        for (int i=0; i<password.length(); i++) {
            ch = password.charAt(i);
            if (Character.isDigit(ch))
                numberFlag = true;
            else if (Character.isUpperCase(ch))
                capitalFlag = true;
            else if (Character.isLowerCase(ch))
                lowerCaseFlag = true;
            else if ("/*!@#$%^&*()\"{}_[]|\\?/<>,.".contains(String.valueOf(ch)));
                symbolFlag = true;
        }
        if (capitalFlag && lowerCaseFlag && numberFlag && symbolFlag) return true;
        else return false;
    }
}
