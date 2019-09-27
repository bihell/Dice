package com.bihell.dice.security;

import com.bihell.dice.model.domain.User;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

import static com.bihell.dice.utils.DiceConsts.ACCESS_TOKEN_CACHE_PREFIX;
import static com.bihell.dice.utils.DiceConsts.REFRESH_TOKEN_CACHE_PREFIX;
import static com.bihell.dice.utils.DiceConsts.TOKEN_ACCESS_CACHE_PREFIX;
import static com.bihell.dice.utils.DiceConsts.TOKEN_REFRESH_CACHE_PREFIX;

/**
 * Security utilities.
 *
 * @author johnniang
 * @date 19-4-29
 */
public class SecurityUtil {

    private SecurityUtil() {
    }

    @NonNull
    public static String buildAccessTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return ACCESS_TOKEN_CACHE_PREFIX + user.getId();
    }

    @NonNull
    public static String buildRefreshTokenKey(@NonNull User user) {
        Assert.notNull(user, "User must not be null");

        return REFRESH_TOKEN_CACHE_PREFIX + user.getId();
    }

    @NonNull
    public static String buildTokenAccessKey(@NonNull String accessToken) {
        Assert.hasText(accessToken, "Access token must not be blank");

        return TOKEN_ACCESS_CACHE_PREFIX + accessToken;
    }

    @NonNull
    public static String buildTokenRefreshKey(@NonNull String refreshToken) {
        Assert.hasText(refreshToken, "Refresh token must not be blank");

        return TOKEN_REFRESH_CACHE_PREFIX + refreshToken;
    }
}
