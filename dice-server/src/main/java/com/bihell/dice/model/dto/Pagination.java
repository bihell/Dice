package com.bihell.dice.model.dto;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * 分页Bean
 *
 * @author bihell
 * @since 2017/10/23 11:44
 */
@Data
public class Pagination<T> {
    private long pageNum;
    private long pageSize;
    private long total;
    private long pages;
    private List<T> list;

    public Pagination() {
    }

    @SuppressWarnings("unchecked")
    public Pagination(IPage page) {
        pageNum = page.getCurrent();
        pageSize = page.getSize();
        total = page.getTotal();
        pages =page.getPages();
        list = page.getRecords();
    }
}
