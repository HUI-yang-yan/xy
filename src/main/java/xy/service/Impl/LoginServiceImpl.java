package xy.service.Impl;

import ch.qos.logback.core.util.MD5Util;
import cn.hutool.crypto.digest.DigestUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import xy.mapper.LoginMapper;
import xy.service.LoginService;
import xy.utils.SaltUtil;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 登录校验
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username, String password) {
        String realPassword = loginMapper.loginByPassword(username);
        String salt = loginMapper.getSaltByUsername(username);
        if(salt == null || salt.isEmpty()) {
            //1.判断盐是否为空,如果为空,直接返回错误
            return false;
        } else{
            //2.正确,判断密码是否正确
            String InputPassword = DigestUtil.md5Hex(password + salt);
            return isEqual(InputPassword,realPassword);
        }
    }

    private boolean isEqual(String inputPassword, String realPassword) {
        return inputPassword.equals(realPassword);
    }
}
