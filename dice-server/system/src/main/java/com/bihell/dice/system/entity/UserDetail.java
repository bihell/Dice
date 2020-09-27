package com.bihell.dice.system.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.NonNull;

/**
 * User detail. todo 待调整
 *
 * @author johnniang
 */
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

    private User user;

    /**
     * Gets user info.
     *
     * @return user info
     */
    @NonNull
    public User getUser() {
        return user;
    }

    /**
     * Sets user info.
     *
     * @param user user info
     */
    public void setUser(User user) {
        this.user = user;
    }
}
