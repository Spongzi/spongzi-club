package com.spongzi.subject.infra.basic.esRepo;

import com.spongzi.subject.infra.basic.entity.SubjectInfoEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * 主题专家系统知识库
 *
 * @author spong
 * @date 2023/12/23
 */
@Component
public interface SubjectEsRepository extends ElasticsearchRepository<SubjectInfoEs, Long> {
}
