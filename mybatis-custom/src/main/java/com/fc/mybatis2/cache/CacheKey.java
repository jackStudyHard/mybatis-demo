package com.fc.mybatis2.cache;

/**
 * @author lize
 * @date 6/23/19 9:58 PM
 */
public class CacheKey {
    private static final int DEFAULT_HASHCODE = 17; // 默认哈希值
    private static final int DEFAULT_MULTIPLIER = 37; // 倍数

    private int hashCode;
    private int count;
    private int multiplier;

    /**
     * 构造函数
     */
    public CacheKey() {
        this.hashCode = DEFAULT_HASHCODE;
        this.count = 0;
        this.multiplier = DEFAULT_MULTIPLIER;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof CacheKey)) {
            return false;
        }

        final CacheKey cacheKey = (CacheKey) object;

        if (hashCode != cacheKey.hashCode) {
            return false;
        }
        if (count != cacheKey.count) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    /**
     * 返回CacheKey的值
     * @return
     */
    public int getCode() {
        return hashCode;
    }

    /**
     * 计算CacheKey中的HashCode
     * @param object
     */
    public void update(Object object) {
        int baseHashCode = object == null ? 1 : object.hashCode();
        count++;
        baseHashCode *= count;
        hashCode = multiplier * hashCode + baseHashCode;
    }
}
