package cc.occs.auth.model;

import cc.occs.auth.domain.UserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {

    private String token;

    private UserDetail userDetail;

}
