package cc.occs.auth.service;

import cc.occs.auth.model.UserInfoResponse;

public interface SysAuthService {

    UserInfoResponse login(String username, String password);

}
