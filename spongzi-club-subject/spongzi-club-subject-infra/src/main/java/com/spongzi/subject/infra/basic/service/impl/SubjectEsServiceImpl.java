package com.spongzi.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.spongzi.subject.infra.basic.entity.SubjectInfoEs;
import com.spongzi.subject.infra.basic.esRepo.SubjectEsRepository;
import com.spongzi.subject.infra.basic.service.SubjectEsService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Subject Es 服务 实现类
 *
 * @author spong
 * @date 2023/12/23
 */
@Service
@Slf4j
public class SubjectEsServiceImpl implements SubjectEsService {

    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Resource
    private SubjectEsRepository subjectEsRepository;

    @Override
    public void createIndex() {
        IndexOperations indexOperations = elasticsearchRestTemplate.indexOps(SubjectInfoEs.class);
        indexOperations.create();
        Document mapping = indexOperations.createMapping(SubjectInfoEs.class);
        indexOperations.putMapping(mapping);
    }

    @Override
    public void addDoc() {
        LinkedList<SubjectInfoEs> list = new LinkedList<>();
        list.add(new SubjectInfoEs(1L, "Redis是什么", "Redis是一个缓存", "spongzi", new Date()));
        list.add(new SubjectInfoEs(2L, "MySql是什么", "MySql是一个数据库", "spongzi", new Date()));
        subjectEsRepository.saveAll(list);
    }

    @Override
    public void find() {
        Iterable<SubjectInfoEs> all = subjectEsRepository.findAll();
        for (SubjectInfoEs subjectInfoEs : all) {
            log.info("subjectInfoEs: {}", JSON.toJSONString(subjectInfoEs));
        }
    }

    @Override
    public void search() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchQuery("subjectName", "redis"))
                .build();
        SearchHits<SubjectInfoEs> search = elasticsearchRestTemplate.search(nativeSearchQuery, SubjectInfoEs.class);
        List<SearchHit<SubjectInfoEs>> searchHits = search.getSearchHits();
        log.info("searchHits: {}", JSON.toJSONString(searchHits));
    }
}
