package ch.zhaw.project.easycts.services;

import ch.zhaw.project.easycts.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private HashMap<String, User> tokens;

    public TokenServiceImpl() {
        tokens = new HashMap<>();
    }

    public String addTokenForUser(User user) {
        String token = generateAccessToken();
        tokens.put(token, user);
        return token;
    }

    private String generateAccessToken() {
        return "token-" + UUID.randomUUID();
    }

    public User getUserByToken(String token) {
        return tokens.get(token);
    }

    public void removeAccessToken(String token) {
        tokens.remove(token);
    }
}
