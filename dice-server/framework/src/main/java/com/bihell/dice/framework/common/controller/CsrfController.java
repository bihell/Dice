package com.bihell.dice.framework.common.controller;

import com.bihell.dice.framework.log.annotation.OperationLogIgnore;
import com.bihell.dice.framework.util.UUIDUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * CSRF 供swagger调用
 **/
@ApiIgnore
@OperationLogIgnore
@RestController
public class CsrfController {

    @RequestMapping(value = "/csrf", method = {RequestMethod.GET, RequestMethod.POST})
    public String csrf() {
        return UUIDUtil.getUuid();
    }

}
