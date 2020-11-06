package com.key.dwsurvey.dao;

import com.key.common.dao.BaseDao;
import com.key.dwsurvey.entity.AnChenRadio;
import com.key.dwsurvey.entity.Question;

/**
 * 矩陈单选题数据 interface
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://dwsurvey.net
 *
 */
public interface AnChenRadioDao extends BaseDao<AnChenRadio, String> {

	void findGroupStats(Question question);

}
