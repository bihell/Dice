package com.bihell.dice.system.service.impl;

import com.bihell.dice.system.service.Authentication;
import com.bihell.dice.system.service.SecurityContext;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Security context implementation.
 *
 * @author johnniang
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SecurityContextImpl implements SecurityContext {

    private Authentication authentication;

    @Override
    public Authentication getAuthentication() {
        return authentication;
    }

    @Override
    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

}
