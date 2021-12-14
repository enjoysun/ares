package cn.gov.zcy.ares.adrasteia.core.parser;

import cn.gov.zcy.ares.adrasteia.core.LogPersistContextHandler;
import cn.gov.zcy.ares.adrasteia.meta.LogPersistContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-12-13 10:15:23
 */
@Slf4j
public class PersistBeforeFactory {
    private final static ConcurrentHashMap<String, LogPersistContextHandler> BEFORE_MAP = new ConcurrentHashMap<>(32);

    public static void getInvoke(Class<? extends LogPersistContextHandler> logPersistContextHandler, LogPersistContext logPersistContext) {
        String name = logPersistContextHandler.getName();
        LogPersistContextHandler handler = BEFORE_MAP.get(name);
        if (null != handler) {
            handler.persistentBefore(logPersistContext);
            return;
        }
        LogPersistContextHandler instance = (LogPersistContextHandler) Proxy.newProxyInstance(logPersistContextHandler.getClassLoader(), logPersistContextHandler.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                method.invoke(logPersistContextHandler.newInstance(), args);
                return null;
            }
        });
        instance.persistentBefore(logPersistContext);
        BEFORE_MAP.put(name, instance);
    }
}
