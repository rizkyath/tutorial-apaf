package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.UserModel;

import java.util.List;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
    List<UserModel> getListUser();
    UserModel getUserByUsername(String username);
    UserModel deleteById(String id);
    boolean checkPassword(String password);
}
