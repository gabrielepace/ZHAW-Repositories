package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.User;

public interface TokenService {

    String addTokenForUser(User user);

    User getUserByToken(String token);

    void removeAccessToken(String token);
}
