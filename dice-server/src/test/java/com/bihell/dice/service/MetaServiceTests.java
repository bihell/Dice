package com.bihell.dice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MetaServiceTests {

    @Autowired
    MetaService metaService;

    @Test
    public void getPublishMetaDtos() {
        metaService.getPublishMetaDtos("category").forEach(System.out::println);
    }

    @Test
    public void selectMetaDtos() {
        metaService.getMetaDtos("category", null, null).forEach(System.out::println);
    }

    @Test
    public void deleteMeta() {
        metaService.deleteMeta("asdf", "tag");
    }

    @Test
    public void saveMeta() {
        metaService.saveMeta("test1", "category");
    }

    @Test
    public void updateMeta() {
        metaService.updateMeta(4, "fgg1", "category");
    }

    @Test
    public void saveOrRemoveMetas() {
        metaService.saveOrRemoveMetas("test1", "category", 2);
    }
}
