package io.tortoise.service.panel;

import io.tortoise.base.domain.PanelSubject;
import io.tortoise.base.domain.PanelSubjectExample;
import io.tortoise.base.mapper.PanelSubjectMapper;
import io.tortoise.controller.request.panel.PanelSubjectRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Author: wangjiahao
 * Date: 2021-05-06
 * Description:
 */
@Service
public class PanelSubjectService {

    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Resource
    private PanelSubjectMapper panelSubjectMapper;

    public List<PanelSubject> query(PanelSubjectRequest request){
        PanelSubjectExample example = new PanelSubjectExample();
        example.setOrderByClause( "create_time asc");
        return panelSubjectMapper.selectByExampleWithBLOBs(example);
    }

    public List querySubjectWithGroup(PanelSubjectRequest request){
        List result = new ArrayList();
        int pageSize = 4;
        PanelSubjectExample example = new PanelSubjectExample();
        example.setOrderByClause( "create_time asc");
        List<PanelSubject> allInfo  = panelSubjectMapper.selectByExampleWithBLOBs(example);
        for(int i =0;i<allInfo.size();i=i+pageSize){
            List<PanelSubject> tmp = allInfo.subList(i,i+pageSize<allInfo.size()?i+pageSize:allInfo.size());
            result.add(tmp);
        }
        return result;
    }

    public void update(PanelSubject request){
        if(StringUtils.isEmpty(request.getId())){
            request.setId(UUID.randomUUID().toString());
            request.setCreateTime(System.currentTimeMillis());
            request.setType("self");
            request.setName("个人主题");
            panelSubjectMapper.insertSelective(request);
        }else{
            request.setUpdateTime(System.currentTimeMillis());
            panelSubjectMapper.updateByPrimaryKeySelective(request);
        }
    }

    public void delete(String id){
        Assert.notNull(id,"subjectId should not be null");
        panelSubjectMapper.deleteByPrimaryKey(id);
    }



}
