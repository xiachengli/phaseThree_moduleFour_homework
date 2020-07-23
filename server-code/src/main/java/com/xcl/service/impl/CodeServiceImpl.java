package com.xcl.service.impl;

import com.xcl.feign.EmailFeign;
import com.xcl.mapper.CodeMapper;
import com.xcl.pojo.Code;
import com.xcl.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    EmailFeign emailFeign;

    @Autowired
    CodeMapper codeMapper;

    @Override
    public boolean saveAndSend(String email, String code) {
        Long id =  UUID.randomUUID().getLeastSignificantBits();
        emailFeign.send(email,code);
        codeMapper.save(id,email,code);
        return true;
    }

    @Override
    public List<Code> queryByEmail(String email) {
        return codeMapper.queryByEmail(email);
    }
}
