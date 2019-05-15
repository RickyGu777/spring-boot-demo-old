package com.example.servicehi.service.impl;

import com.example.servicehi.dao.ShareTicketImgDao;
import com.example.servicehi.entity.HotWord;
import com.example.servicehi.entity.ShareTicketImg;
import com.example.servicehi.service.HotWordService;
import com.example.servicehi.service.ShareTicketImgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ShareTicketImgServiceImpl<T extends ShareTicketImg> implements ShareTicketImgService<T> {
    @Autowired
    private ShareTicketImgDao<T> shareTicketImgDao;

    @Autowired
    private HotWordService<HotWord> hotWordService;

    @Override
    public void insert(T t) {
        t.setCreateDate(new Date());
        t.setModiDate(t.getCreateDate());
        shareTicketImgDao.insert(t);
    }

    @Override
    @Transactional
    public List<T> selectTitleAndTips(T t) {
        HotWord hotWord = new HotWord();
        hotWord.setHotWord(t.getTitle());
        hotWord.setCreateDate(new Date());
        HotWord todayHotWord = hotWordService.selectTodayHotWord(hotWord);
        if (todayHotWord == null) {
            hotWordService.insert(hotWord);
        } else {
            hotWordService.updateTimes(todayHotWord);
        }
        PageHelper.startPage(t.getPage(), t.getSize());
        return new PageInfo(shareTicketImgDao.selectTitleAndTips(t)).getList();
    }
}
