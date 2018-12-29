package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.repository.JrResourceRepository;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传 service
 * @author hf
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JrResourceServiceImpl extends BaseServiceImpl<JrResource> implements JrResourceService {

    /** 文件上传 repository */
    private final JrResourceRepository jrResourceRepository;

    @Override
    protected BaseRepository<JrResource> getBaseRepository() {
        return jrResourceRepository;
    }

    @Override
    public ExampleMatcher defaultExampleMatcher() {
        return super.defaultExampleMatcher()
                .withMatcher("connectid", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("url", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("type", ExampleMatcher.GenericPropertyMatchers.contains());
    }

    @Override
    public List<JrResource> saveFile(MultipartFile[] multipartFile, String type, String id) {

        List<JrResource> jrResourceList=new ArrayList<>();
        File file=new File(String.format("D://JURONG//%s", type));
        if(file.exists()==false){
            file.mkdir();
        }
        file=new File("D://JURONG"+"//"+type+"//"+id);
        if(file.exists()==false){
            file.mkdir();
        }
        for (MultipartFile multipartFile1:multipartFile){
            String originalFilename = multipartFile1.getOriginalFilename();
            //新的文件名字
            String newFileName = UUID.randomUUID()+originalFilename;
            //封装上传文件位置的全路径
            File targetFile = new File(file,newFileName);
            //把本地文件上传到封装上传文件位置的全路径
            try {
                multipartFile1.transferTo(targetFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JrResource jrResource = new JrResource();
            jrResource.setUrl("/"+type+"/"+id+"/"+newFileName);
            jrResource.setConnectId(id);
            jrResource.setType(type);
           JrResource jrResource1= super.save(jrResource);
           jrResourceList.add(jrResource1);
        }
        //写入

        return  jrResourceList;
    }
}