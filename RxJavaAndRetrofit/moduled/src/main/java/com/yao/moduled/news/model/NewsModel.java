package com.yao.moduled.news.model;

import com.yao.lib_common.model.entity.news.NewsRequestModel;
import com.yao.lib_common.network.INewService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:30
 * @Version:
 */

public class NewsModel extends Model {

    private INewService service;

    @Override
    public Observable<NewsRequestModel> loadNews(String url, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("type", type);
        service.getNewList(url, map);
        return null;
    }
}
