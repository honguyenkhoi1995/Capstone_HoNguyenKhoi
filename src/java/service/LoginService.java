package service;

import dao.UserDAO;
import model.User;

public class LoginService {
    private final UserDAO userDAO = new UserDAO();
    public User login(String email,String password) throws Exception {
        return userDAO.login(email,password);
    }
}
