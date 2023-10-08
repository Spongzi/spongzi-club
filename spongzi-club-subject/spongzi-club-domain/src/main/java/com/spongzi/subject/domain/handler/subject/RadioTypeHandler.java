package com.spongzi.subject.domain.handler.subject;

import com.spongzi.subject.common.enums.IsDeletedEnum;
import com.spongzi.subject.common.enums.SubjectInfoTypeEnum;
import com.spongzi.subject.domain.convert.SubjectRadioConvert;
import com.spongzi.subject.domain.entity.SubjectAnswerBO;
import com.spongzi.subject.domain.entity.SubjectInfoBO;
import com.spongzi.subject.infra.basic.entity.SubjectRadio;
import com.spongzi.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 单选题目的策略类型
 *
 * @author spong
 * @date 2023/10/08
 */
@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        // 单选题目的插入
        List<SubjectRadio> subjectRadioList = new LinkedList<>();
        List<SubjectAnswerBO> optionList = subjectInfoBO.getOptionList();
        // TODO 添加if判断，防止出现空指针
        for (SubjectAnswerBO option : optionList) {
            SubjectRadio subjectRadio = SubjectRadioConvert.INSTANCE.convertAnswerBoToSubjectRadio(option);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeletedEnum.UN_DELETED.getCode());
            subjectRadioList.add(subjectRadio);
        }
        subjectRadioService.batchInsert(subjectRadioList);
    }
}
