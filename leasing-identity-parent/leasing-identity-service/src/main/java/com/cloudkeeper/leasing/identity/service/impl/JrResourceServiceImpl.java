package com.cloudkeeper.leasing.identity.service.impl;

import com.cloudkeeper.leasing.base.repository.BaseRepository;
import com.cloudkeeper.leasing.base.service.impl.BaseServiceImpl;
import com.cloudkeeper.leasing.identity.domain.City;
import com.cloudkeeper.leasing.identity.domain.Country;
import com.cloudkeeper.leasing.identity.domain.JrResource;
import com.cloudkeeper.leasing.identity.domain.Town;
import com.cloudkeeper.leasing.identity.repository.CityRepository;
import com.cloudkeeper.leasing.identity.repository.CountryRepository;
import com.cloudkeeper.leasing.identity.repository.JrResourceRepository;
import com.cloudkeeper.leasing.identity.repository.TownRepository;
import com.cloudkeeper.leasing.identity.service.JrResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 文件上传 service
 * @author hf
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JrResourceServiceImpl extends BaseServiceImpl<JrResource> implements JrResourceService {

    /** 文件上传 repository */
    private final JrResourceRepository jrResourceRepository;
    private final CountryRepository countryRepository;
    private  final TownRepository townRepository;
    private  final CityRepository cityRepository;

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
    public List<JrResource> saveFile(MultipartFile[] multipartFile, String type, String id,String countryid) {
        File file;
        String countryname=null;
        String townname=null;
        String cityname=null;
        String nian=null;
        List<JrResource> jrResourceList=new ArrayList<>();
        if("record".equals(type)){
            file=new File("D://JURONG//record");
            if (file.exists() == false) {
                file.mkdir();
            }
            Optional<Country> country=countryRepository.findById(countryid);
            countryname=country.get().getName();
            Optional<Town> town=townRepository.findById(country.get().getTownid());
           townname=town.get().getName();
            Optional<City> city=cityRepository.findById(town.get().getCityid());
             cityname=city.get().getName();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            nian=sdf.format(date);
          file=new File(file+"//"+cityname);
            if (file.exists() == false) {
                file.mkdir();
            }
            file=new File(file+"//"+nian);
            if (file.exists() == false) {
                file.mkdir();
            }
            file=new File(file+"//"+townname);
            if (file.exists() == false) {
                file.mkdir();
            }
            file=new File(file+"//"+countryname);
            if (file.exists() == false) {
                file.mkdir();
            }
            file=new File(file+"//"+id);
            if (file.exists() == false) {
                file.mkdir();
            }
        }else {
            file = new File(String.format("D://JURONG//%s", type));
            if (file.exists() == false) {
                file.mkdir();
            }
            file = new File("D://JURONG" + "//" + type + "//" + id);
            if (file.exists() == false) {
                file.mkdir();
            }
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

            try {
                Thumbnails.of(file+"//"+newFileName).size(240,240).toFile(file+"//new"+newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String url;
            String thumb;

            if("record".equals(type)) {
                url="/record/"+cityname+"/"+nian+"/"+townname+"/"+countryname+"/"+id+"/"+newFileName;
                thumb="/record/"+cityname+"/"+nian+"/"+townname+"/"+countryname+"/"+id+"/new"+newFileName;
                jrResource.setUrl(url);
                jrResource.setThumbnail(thumb);

            }else {
                url="/" + type + "/" + id + "/" + newFileName;
                thumb="/" + type + "/" + id + "/new" + newFileName;
                jrResource.setUrl(url);
                jrResource.setThumbnail(thumb);
            }


            jrResource.setConnectId(id);
            jrResource.setType(type);
           JrResource jrResource1= super.save(jrResource);
           jrResourceList.add(jrResource1);
        }
        //写入

        return  jrResourceList;
    }
    public List<JrResource> findByConnectId(String connectid){
        return jrResourceRepository.findByConnectId(connectid);
    }

    @Override
    public void deleteMore(List<String> ids) {
        ids.stream().forEach(item -> jrResourceRepository.deleteById(item));
    }

}