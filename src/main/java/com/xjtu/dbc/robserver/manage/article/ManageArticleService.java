package com.xjtu.dbc.robserver.manage.article;

import com.xjtu.dbc.robserver.common.model.inform.InformDto;

import java.util.Map;

public interface ManageArticleService {
    Map<String, Object> getInformList(InformDto informDto);
}
