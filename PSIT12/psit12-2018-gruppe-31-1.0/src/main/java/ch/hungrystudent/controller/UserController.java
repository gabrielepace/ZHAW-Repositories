package ch.hungrystudent.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ch.hungrystudent.dataaccess.UserDao;
import ch.hungrystudent.dataaccess.UserDaolmpl;
import ch.hungrystudent.domain.Marker;
import ch.hungrystudent.domain.User;

/**
 * User Controller class
 *
 * @author PSIT2 - Gruppe 31 (Hungry Student) ---------------------------->
 * Erwin Tran (tranerw1), Gabriele Pace (pacegab1), Aleksandra Timofeeva
 * (timofale), Semanur Cerkez (cerkesem)
 */
public class UserController {

    private static final String EMAIL_REGEX= "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static final String NAME_REGEX = "^[a-zA-Z0-9_.-]{4,30}$";
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);

    private UserDao userDao;
    private User user;
    private List<Marker> createdMarkers;
    private List<Marker> favouriteMarkers;

    public UserController() {

        this(new UserDaolmpl(), new User(-1, "", "", ""));
    }

    private UserController(UserDao userDao, User user) {

        this.userDao = userDao;
        this.user = user;
        this.createdMarkers = new ArrayList<>();
        this.favouriteMarkers = new ArrayList<>();
    }

    public UserController(UserDao userDao) {

        this(userDao, new User(-1, "", "", ""));
    }

    public boolean doAction(String action, User user) {
        if (null != action) {

            switch (action) {
                case "add":
                    return register(user);
                case "edit":
                    return editUser(user);
                case "login":
                    return login(user);
                case "logout":
                    logout();
                    // returns false so that user will stay on login page
                    return false;
                case "remove":
                    return removeUser(user);
                default:
                    // do nothing
            }
        }
        return false;
    }

    /**
     * Loads every marker created by user
     */
    public void loadCreatedMarkers() {
        if (user != null) {
            if (user.getId() != -1) {
                this.createdMarkers = userDao.getCreatedOfUser(user.getId());
            }
        } else {
            this.createdMarkers = new ArrayList<>();
        }
    }

    /**
     * Loads every marker favourited by user
     */
    public void loadFavouriteMarkers() {

        if (user == null) {
            //noinspection ConstantConditions
            Objects.requireNonNull(null);
        }
        this.favouriteMarkers = userDao.getFavouritesOfUser(user.getId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Marker> getCreatedMarkers() {
        return createdMarkers;
    }

    public List<Marker> getFavouriteMarkers() {
        return favouriteMarkers;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean login(User user) {
        if (!("".equals(user.getEmail())) && !("".equals(user.getPassword())) && emailIsValid(user.getEmail())) {
            this.user = userDao.getUser(user.getEmail(), user.getPassword());
            return this.user.getId() != -1;
        } else {
            return false;
        }
    }

    private boolean editUser(User user) {
        if ((user != null)  && usernameIsValid(user.getUsername()) && emailIsValid(user.getEmail())) {
        	
            userDao.editUser(user);
            this.user = user;
            return true;
        } else {
            return false;
        }
    }

    public boolean removeUser(User user) {
        if (user != null) {
            userDao.removeUser(user);
            this.user = new User();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Creates a new account with the given user
     *
     * @param newUser user to be added
     * @return true if a user could be created, false if username/email are already taken
     * or user couldn't be created
     */
    public boolean register(User newUser) {

        if (!newUser.getUsername().isEmpty()
        		&& usernameIsValid(newUser.getUsername())
                && !newUser.getEmail().isEmpty()
                && emailIsValid(newUser.getEmail())
                && !newUser.getPassword().isEmpty()
                && userDao.checkAvailability(newUser.getUsername(), newUser.getEmail())) {
            long userId = userDao.createUser(newUser);
            newUser.setId(userId);
            this.user = newUser;
            return true;
        }
        return false;
    }

    private void logout() {
        this.user = new User();
    }
    
	public boolean emailIsValid(String email) {
        
		Matcher matcher = EMAIL_PATTERN.matcher(email);
		return matcher.matches();
	}

	public boolean usernameIsValid(String username) {

        Matcher matcher = NAME_PATTERN.matcher(username);
		return matcher.matches();
	}
}