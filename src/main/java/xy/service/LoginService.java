package xy.service;

public interface LoginService {
    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    boolean login(String username, String password);
}
