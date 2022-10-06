package ccut.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ccut.mapper.BaseAreaMapper;
import ccut.model.pojo.BaseArea;
import org.springframework.stereotype.Service;

import ccut.service.BaseAreaService;

@Service
public class BaseAreaServiceImpl extends ServiceImpl<BaseAreaMapper, BaseArea> implements BaseAreaService {}

