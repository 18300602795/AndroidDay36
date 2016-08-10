package com.tomato.z.androidday36.service;

/**
 * PACKAGE_NAME: com.tomato.z.androidday36.service
 * FUNCTIONAL_DESCRIPTION：
 * CREATE_BY： 闫
 * CREATE_TIME: 2016/8/4 14:43
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME：
 */
public interface IService {
    //把我们之前想要暴露出来的房都写入接口中
    public void callPlayerMusic();

    public void callPauseMusic();

    public void callRePlayerMusic();

    public void callSeekToPosition(int position);

}
