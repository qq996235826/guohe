package com.guohe.app.service;

import com.guohe.app.dto.ResultDTO;
import com.guohe.app.mapper.StuInfoExtMapper;
import com.guohe.app.mapper.StuInfoMapper;
import com.guohe.app.model.StuInfoExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Time;
import java.util.*;

/**
 * @version/版本: V1.0
 * @author/作者: 邓浩然
 * @description/描述: 负责统计用户组合,实现数据可视化的service
 * @data/创建日期: 2020-10-9 23:04
 **/
@Service
public class StatisticsService {
    @Resource
    StuInfoMapper stuInfoMapper;

    @Resource
    StuInfoExtMapper stuInfoExtMapper;

    public ResultDTO getProportion()
    {
        //获得年份
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DATE);
        //获得当前年份后两位
        year=year%100;
        //获得四年的学号开头
        ArrayList<String> years=new ArrayList<>();
        for(int a=0;a<4;a++)
        {
            //年份的String类型
            years.add(String.valueOf(year - a)+"%");
        }

        //最近10天的用户量
        Map<String,Long> userAdd=new HashMap<>();
        Calendar cal1 = Calendar.getInstance();
        for(int a=0;a<10;a++)
        {
            cal1.add(Calendar.DATE, -1);
            int year1=cal1.get(Calendar.MONTH)+1;
            userAdd.put(cal1.get(Calendar.YEAR)+"-"+year1+"-"+cal1.get(Calendar.DATE),stuInfoExtMapper.countByDay(a));
        }



        //获得苏州理工的学生数量
        StuInfoExample sZExample =new StuInfoExample();
        //获得大一学生的数量
        sZExample .createCriteria().andAcademyEqualTo("苏州理工学院").andUidLike(years.get(0));
        Long suZouLiGong1 =stuInfoMapper.countByExample(sZExample );
        //获得大二学生的数量
        sZExample .clear();
        sZExample .createCriteria().andAcademyEqualTo("苏州理工学院").andUidLike(years.get(1));
        Long suZouLiGong2 =stuInfoMapper.countByExample(sZExample );
        //获得大三学生的数量
        sZExample .clear();
        sZExample .createCriteria().andAcademyEqualTo("苏州理工学院").andUidLike(years.get(2));
        Long suZouLiGong3 =stuInfoMapper.countByExample(sZExample );
        //获得大四学生的数量
        sZExample .clear();
        sZExample .createCriteria().andAcademyEqualTo("苏州理工学院").andUidLike(years.get(3));
        Long suZouLiGong4 =stuInfoMapper.countByExample(sZExample );

        //获得张家港的学生数量
        StuInfoExample zJGExample =new StuInfoExample();
        //获得大一学生的数量
        zJGExample .createCriteria().andAcademyEqualTo("张家港校区管委会").andUidLike(years.get(0));
        Long zhangJiaGang1 =stuInfoMapper.countByExample(zJGExample );
        //获得大二学生的数量
        zJGExample .clear();
        zJGExample .createCriteria().andAcademyEqualTo("张家港校区管委会").andUidLike(years.get(1));
        Long zhangJiaGang2 =stuInfoMapper.countByExample(zJGExample );
        //获得大三学生的数量
        zJGExample .clear();
        zJGExample .createCriteria().andAcademyEqualTo("张家港校区管委会").andUidLike(years.get(2));
        Long zhangJiaGang3 =stuInfoMapper.countByExample(zJGExample );
        //获得大四学生的数量
        zJGExample .clear();
        zJGExample .createCriteria().andAcademyEqualTo("张家港校区管委会").andUidLike(years.get(3));
        Long zhangJiaGang4 =stuInfoMapper.countByExample(zJGExample );

        //获得长山校区学生(没有大一的)
        StuInfoExample csexample =new StuInfoExample();
        //长山大二
        //不在张家港和苏州的大二新生就在长山校区
        csexample .createCriteria().andAcademyNotEqualTo("张家港校区管委会").andAcademyNotEqualTo("苏州理工学院").andUidLike(years.get(1));
        Long changShan2=stuInfoMapper.countByExample(csexample );
        //长山大三
        csexample .clear();
        csexample .createCriteria().andAcademyNotEqualTo("张家港校区管委会").andAcademyNotEqualTo("苏州理工学院").andUidLike(years.get(2));
        Long changShan3=stuInfoMapper.countByExample(csexample );
        //长山大四
        csexample .clear();
        csexample .createCriteria().andAcademyNotEqualTo("张家港校区管委会").andAcademyNotEqualTo("苏州理工学院").andUidLike(years.get(3));
        Long changShan4=stuInfoMapper.countByExample(csexample );

        //获得东区学生数量(东区只有大一的)
        StuInfoExample eastExample =new StuInfoExample();
        //不在张家港和苏州的大一新生就在东区
        eastExample .createCriteria().andAcademyNotEqualTo("张家港校区管委会").andAcademyNotEqualTo("苏州理工学院").andUidLike(years.get(0));
        Long dongQu=stuInfoMapper.countByExample(eastExample );

        //获得果核用户总数(所有用户)
        StuInfoExample example=new StuInfoExample();
        Long all=stuInfoMapper.countByExample(example);

        //学生合计情况
        //东校区就是上面的
        //长山校区学生总数
        Long changShan=changShan2+changShan3+changShan4;
        //苏州理工
        Long suZouLiGong=suZouLiGong1+suZouLiGong2+suZouLiGong3+suZouLiGong4;
        //张家港
        Long zhangJiaGang=zhangJiaGang1+zhangJiaGang2+zhangJiaGang3+zhangJiaGang4;
        //在校用户(只考虑4年内的学生)
        Long inSchool=changShan+suZouLiGong+zhangJiaGang+dongQu;

        //年级分布
        Long grade1=dongQu+zhangJiaGang1+suZouLiGong1;
        Long grade2=changShan2+zhangJiaGang2+suZouLiGong2;
        Long grade3=changShan3+zhangJiaGang3+suZouLiGong3;
        Long grade4=changShan4+zhangJiaGang4+suZouLiGong4;

        //按校区分布的数据
        Map<String,Long> place=new HashMap<>();
        place.put("长山校区学生总数",changShan);
        place.put("苏州理工校区学生总数",suZouLiGong);
        place.put("张家港校区学生总数",zhangJiaGang);
        place.put("梦溪校区学生总数",dongQu);
        //按年级分布的数据
        Map<String,Long> grade=new HashMap<>();
        grade.put("大一",grade1);
        grade.put("大二",grade2);
        grade.put("大三",grade3);
        grade.put("大四",grade4);

        //各个校区的年级分布
        Map<String,Long> gradeOfChangShan=new HashMap<>();
        gradeOfChangShan.put("长山校区大二",changShan2);
        gradeOfChangShan.put("长山校区大三",changShan3);
        gradeOfChangShan.put("长山校区大四",changShan4);
        Map<String,Long> gradeOfSuZou=new HashMap<>();
        gradeOfSuZou.put("苏州理工校区大一",suZouLiGong1);
        gradeOfSuZou.put("苏州理工校区大二",suZouLiGong2);
        gradeOfSuZou.put("苏州理工校区大三",suZouLiGong3);
        gradeOfSuZou.put("苏州理工校区大四",suZouLiGong4);
        Map<String,Long> gradeOfZangJiaGang=new HashMap<>();
        gradeOfZangJiaGang.put("张家港校区大一",zhangJiaGang1);
        gradeOfZangJiaGang.put("张家港校区大二",zhangJiaGang2);
        gradeOfZangJiaGang.put("张家港校区大三",zhangJiaGang3);
        gradeOfZangJiaGang.put("张家港校区大四",zhangJiaGang4);
        Map<String,Long> gradeOfMengXi=new HashMap<>();
        gradeOfMengXi.put("梦溪校区大一(只有大一)",dongQu);
        Map<String,Long> allData=new HashMap<>();
        allData.put("在校用户(4年内的学生)",inSchool);
        allData.put("用户总数",all);

        //列表存放所有结果map
        ArrayList<Map<String,Long>> result=new ArrayList<>();
        result.add(place);
        result.add(grade);
        result.add(gradeOfChangShan);
        result.add(gradeOfMengXi);
        result.add(gradeOfSuZou);
        result.add(gradeOfZangJiaGang);
        result.add(allData);
        result.add(userAdd);

        return ResultDTO.okOf(result);


    }



}
