package com.bihell.dice.blog.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

/**
 * @author haseochen
 */

public enum PostStatusEnum implements IEnum<Integer> {

    /**
     * Published status.
     */
    PUBLISHED(Constants.PUBLISHED_VALUE),

    /**
     * Draft status.
     */
    DRAFT(Constants.DRAFT_VALUE),

    /**
     * Recycle status.
     */
    RECYCLE(Constants.RECYCLE_VALUE),

    /**
     * Intimate status
     */
    INTIMATE(Constants.INTIMATE_VALUE);

    public static class Constants {
        public static final int PUBLISHED_VALUE = 0;
        public static final int DRAFT_VALUE = 1;
        public static final int RECYCLE_VALUE = 2;
        public static final int INTIMATE_VALUE = 3;
    }

    private final int value;

    PostStatusEnum(int value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
