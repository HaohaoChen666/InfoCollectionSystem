package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.SurveyAnswer;
import com.key.dwsurvey.entity.SurveyStats;

import java.util.Map;

public interface SurveyAnswerDao extends BaseDao<SurveyAnswer, String> {

	void saveAnswer(SurveyAnswer surveyAnswer,
                           Map<String, Map<String, Object>> quMaps);

	SurveyStats surveyStatsData(SurveyStats surveyStats);
}
