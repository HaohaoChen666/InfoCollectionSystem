package com.key.dwsurvey.mapper;

import com.key.common.dao.BaseMapper;
import com.key.dwsurvey.entity.SurveyDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyDetailMapper extends BaseMapper<SurveyDetail> {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int insert(SurveyDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int insertSelective(SurveyDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    SurveyDetail selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(SurveyDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKeyWithBLOBs(SurveyDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_survey_detail
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(SurveyDetail record);


    /**
     * @param
     * @return
     * @description 根据dirId/surveyId查询
     * @author suewong
     * @date 2020/7/13 16:58
     */
    SurveyDetail queryByDirId(@Param("dirId") String dirId);
}