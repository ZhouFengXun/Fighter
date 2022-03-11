package com.fanlan.fighterredis.service;

import com.fanlan.fighterredis.common.vo.Result;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    /**
     * 创建token
     * @return
     */
    Result createToken();

    /**
     * 检验token
     * @param request
     * @return
     */
    boolean checkToken(HttpServletRequest request) throws Exception;

}
