package ccut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ccut.mapper.BaseCityMapper;
import ccut.model.pojo.BaseCity;
import org.springframework.stereotype.Service;


import ccut.service.BaseCityService;

@Service
public class BaseCityServiceImpl extends ServiceImpl<BaseCityMapper, BaseCity> implements BaseCityService {}

