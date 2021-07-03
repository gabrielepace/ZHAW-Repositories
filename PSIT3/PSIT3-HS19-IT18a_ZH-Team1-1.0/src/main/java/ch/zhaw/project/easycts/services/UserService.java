package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.User;

public interface UserService {

    User save(User user);

    User getUser(String username);

    User validateUser(String username, String password);
}
