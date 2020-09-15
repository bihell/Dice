package com.bihell.dice.security.authentication;

import com.bihell.dice.security.UserDetail;
import org.springframework.lang.NonNull;

/**
 * Authentication.
 *
 * @author johnniang
 */
public interface Authentication {

    /**
     * Get user detail.
     *
     * @return user detail
     */
    @NonNull
    UserDetail getDetail();
}
