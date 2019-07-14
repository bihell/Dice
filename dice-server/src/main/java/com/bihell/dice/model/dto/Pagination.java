package com.bihell.dice.model.dto;

import com.github.pagehelper.Page;
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
    private int pageNum;
    private int pageSize;
    private long total;
    private int pages;
    private boolean count;
    private String orderBy;
    private List<T> list;

    public Pagination() {
    }

    @SuppressWarnings("unchecked")
    public Pagination(Page page) {
        pageNum = page.getPageNum();
        pageSize = page.getPageSize();
        total = page.getTotal();
        pages =page.getPages();
        count = page.isCount();
        orderBy = page.getOrderBy();
        list = page.getResult();
    }
}
