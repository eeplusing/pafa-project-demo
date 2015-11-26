package com.ring.front.biz.util;

import java.util.UUID;

/**
 * 用于生成ID的工具类
 *
 * @author Leo Liao, created on 2005-4-4
 * @version 1.0, 2005-4-4
 */
public class IdUtils {
    /**
     * 根据不同的前缀生成唯一的ID，ID格式为C23549517、P2356977，其中C、P为ID前缀。
     * 由参数idPrefix指定。<p/>
     * 注意：
     * 当前方法生成的ID是随机的，并且有可能重复。这是为了降低复杂度，只供演示和学习使用。
     * 在实际应用中，应当通过其它算法来保证ID的唯一性和连续性，例如用数据库的sequence。
     */
    public static String generate(String idPrefix) {
        return idPrefix + System.currentTimeMillis() + (int) (Math.random() * 10);
    }

    public static String getUUID(){
    	return UUID.randomUUID().toString().replace("-", "A");
    }
    
    public static void main(String[] args) {
        System.out.println("id = " + IdUtils.generate("C"));
        System.out.println(getUUID());
    }
}
