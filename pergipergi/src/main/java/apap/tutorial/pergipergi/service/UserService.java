package apap.tutorial.pergipergi.service;

import apap.tutorial.pergipergi.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user);
    String encrypt(String password);
}
