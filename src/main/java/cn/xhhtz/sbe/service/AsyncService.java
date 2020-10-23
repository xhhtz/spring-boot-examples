package cn.xhhtz.sbe.service;

/**
 * 异步服务
 *
 * @author makejava
 * @since 2020-07-31 10:40:02
 */

public interface AsyncService {
    /**
     *
     * @param data
     */
    void asyncWork(String data);

}