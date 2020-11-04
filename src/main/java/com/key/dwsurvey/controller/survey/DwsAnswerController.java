package com.key.dwsurvey.controller.survey;

import com.key.common.QuType;
import com.key.common.base.entity.User;
import com.key.common.base.service.AccountManager;
import com.key.common.plugs.ipaddr.IPService;
import com.key.common.utils.CookieUtils;
import com.key.common.utils.HttpRequestDeviceUtils;
import com.key.common.utils.NumberUtils;
import com.key.constant.CommonConstant;
import com.key.dwsurvey.entity.*;
import com.key.dwsurvey.service.SurveyAnswerManager;
import com.key.dwsurvey.service.SurveyDirectoryManager;
import com.octo.captcha.service.image.ImageCaptchaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 问卷 controller
 * @author KeYuan(keyuan258@gmail.com)
 *
 * https://github.com/wkeyuan/DWSurvey
 * http://www.dwsurvey.net
 *
 */
@Controller
@RequestMapping("/dwsurvey/dws-answer")
@Slf4j
//@Namespaces({ @Namespace("/") })
//@InterceptorRefs({ @InterceptorRef(value = "paramsPrepareParamsStack") })
//@Results({
//        @Result(name = DwsAnswerAction.RESULT_FREQUENCY, location = "/WEB-INF/page/surveydir/survey/stats/response-frequency.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = CrudActionSupport.INPUT, location = "/WEB-INF/page/surveydir/survey/response/response-survey.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.INPUT_IFRAME, location = "/WEB-INF/page/surveydir/survey/response/response-answer-iframe.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_SUCCESS, location = "/WEB-INF/page/content/diaowen-answer/response-success.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_FAILURE, location = "/WEB-INF/page/content/diaowen-answer/response-failure.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_SUCCESS_M, location = "/WEB-INF/page/content/diaowen-answer/response-success-m.jsp", type = Struts2Utils.DISPATCHER),
////        @Result(name = DwsAnswerAction.ANSWER_INPUT_RULE, location = "/WEB-INF/page/content/diaowen-answer/response-input-rule.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_ERROR, location = "/WEB-INF/page/content/diaowen-answer/response-input-error.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_ERROR_M, location = "/WEB-INF/page/content/diaowen-answer/response-input-error-m.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.ANSWER_CODE_ERROR, location = "/dwsurvey/${sid}.html?errorcode=3", type = Struts2Utils.REDIRECT),
//        @Result(name = DwsAnswerAction.ANSWER_CODE_ERROR_M, location = "/survey!answerSurveryMobile.controller?surveyId=${surveyId}&errorcode=3", type = Struts2Utils.REDIRECT),
//        @Result(name = DwsAnswerAction.RELOAD_ANSWER_SUCCESS, location = "dws-answer!answerSuccess.controller?sid=${sid}", type = Struts2Utils.REDIRECT),
//       // @Result(name = DwsAnswerAction.RELOAD_ANSWER_FAILURE, location = "dws-answer!answerFailure.controller?surveyId=${surveyId}", type = Struts2Utils.REDIRECT),
//        @Result(name = DwsAnswerAction.RELOAD_ANSER_ERROR, location = "dws-answer!answerError.controller?surveyId=${surveyId}", type = Struts2Utils.REDIRECT),
//        @Result(name = DwsAnswerAction.RELOAD_ANSER_ERROR_M, location = "dws-answer!answerErrorM.controller?surveyId=${surveyId}", type = Struts2Utils.REDIRECT),
////        @Result(name = DwsAnswerAction.RESPONSE_MSG, location = "/WEB-INF/page/content/diaowen-answer/response-msg.jsp", type = Struts2Utils.DISPATCHER),
//        @Result(name = DwsAnswerAction.RELOAD_ANSWER_SUCCESS_M, location = "dws-answer!answerSuccessM.controller?surveyId=${surveyId}", type = Struts2Utils.REDIRECT),
////        @Result(name = DwsAnswerAction.RESPONSE_MOBILE, location = "dws-answer!answerMobile.controller?surveyId=${surveyId}", type = Struts2Utils.REDIRECT)
//        })

//@AllowedMethods({"saveMobile","answerSuccess","answerMobile","answerFailure","answerError","answerSuccessM","ajaxCheckSurvey"})
public class DwsAnswerController {// extends ActionSupport {
    private static final long serialVersionUID = -2289729314160067840L;

    protected static final String RESULT_FREQUENCY = "resultFrequency";
    protected final static String INPUT_IFRAME = "input_iframe";
    protected final static String ANSWER_SUCCESS = "answerSuccess";
    protected final static String ANSWER_FAILURE = "answerFailure";
    protected final static String ANSWER_ERROR = "answerError";
    protected final static String ANSWER_ERROR_M = "answerErrorM";
    protected final static String ANSWER_SUCCESS_M = "answerSuccessM";
    protected final static String RELOAD_ANSWER_SUCCESS = "reloadAnswerSuccess";
    protected final static String RELOAD_ANSWER_FAILURE = "reloadAnswerFailure";
    protected final static String RELOAD_ANSER_ERROR = "reloadAnserError";
    protected final static String RELOAD_ANSER_ERROR_M = "reloadAnserErrorM";
    protected final static String ANSWER_CODE_ERROR = "answerCodeError";
    protected final static String ANSWER_CODE_ERROR_M = "answerCodeErrorM";
    protected final static String ANSWER_INPUT_RULE = "answer_input_rule";
    protected final static String RELOAD_ANSWER_SUCCESS_M = "reloadAnswerSuccessM";
    protected final static String SURVEY_RESULT = "surveyResult";
    protected final static String RESPONSE_MSG = "responseMsg";
    protected final static String RESPONSE_MOBILE = "responseMobile";

//    private String sid;
    private String surveyId;
    @Autowired
    private SurveyAnswerManager surveyAnswerManager;
    @Autowired
    private SurveyDirectoryManager directoryManager;
    @Autowired
    private IPService ipService;
    @Autowired
    private AccountManager accountManager;
    @Autowired
    private ImageCaptchaService imageCaptchaService;



    @RequestMapping("/execute/{sid}.html")
//    @ResponseBody
    public String execute(HttpServletRequest request,HttpServletResponse response,@PathVariable String sid,Map<String,Object> map) throws Exception {

//        this.sid = sid;
//        HttpServletRequest request = Struts2Utils.getRequest();
//        HttpServletResponse response = Struts2Utils.getResponse();
        request.setAttribute("sid",sid);
        SurveyDirectory directory = directoryManager.getSurveyBySid(sid);
        if (directory != null) {
            surveyId = directory.getId();
            String filterStatus = filterStatus(directory,request);
            if(filterStatus!=null){
                return filterStatus;
            }
            if (HttpRequestDeviceUtils.isMobileDevice(request)) {
                return "redirect:/dwsurvey/dws-answer/answerMobile?surveyId="+surveyId;
            } else {
                String htmlPath = directory.getHtmlPath();
//
//                request.getRequestDispatcher("/" + htmlPath).include(request,
//                        response);
//                map.put("surveyId",surveyId);
//                request.setCharacterEncoding("utf-8");
                request.getRequestDispatcher("/" + htmlPath).forward(request,
                        response);
//                return "/layouts/header";
//                htmlPath = "/" + htmlPath;
//                htmlPath =  htmlPath.substring(0,htmlPath.length()-5);
//                htmlPath = htmlPath.substring(14);
//                request.setAttribute("surveyId",surveyId);

//                return "forward:"+ htmlPath;
//                return "forward:" + htmlPath + "?surveyId="+surveyId;
//                return  "redirect:/dwsurvey/dws-answer/toWjHtml?html="+htmlPath;
                return null;
            }
        }
        return CommonConstant.NONE;
    }

//    @RequestMapping("/toWjHtml")
//    public String viewToHtml(String html){
//        log.info("html="+html);
//        return html;
//    }

    private String filterStatus(SurveyDirectory directory, HttpServletRequest request){
        SurveyDetail surveyDetail = directory.getSurveyDetail();
        int rule = surveyDetail.getRule();
        Integer ynEndNum = surveyDetail.getYnEndNum();
        Integer endNum = surveyDetail.getEndNum();
        Integer ynEndTime = surveyDetail.getYnEndTime();
        Date endTime = surveyDetail.getEndTime();
        Integer anserNum = directory.getAnswerNum();

        if (directory.getSurveyQuNum() <= 0
                || directory.getSurveyState() != 1 ||
                (anserNum!=null && ynEndNum==1 && anserNum > endNum ) ||
                (endTime!=null && ynEndTime==1 && endTime.getTime() < (new Date().getTime())) ){
            request.setAttribute("surveyName", "目前该问卷已暂停收集，请稍后再试");
            request.setAttribute("msg", "目前该问卷已暂停收集，请稍后再试");
            return "/content/diaowen-answer/response-msg";
        }
        if (2 == rule) {
            request.setAttribute("msg", "rule2");
            return "redirect:/dwsurvey/dws-answer/answerError?surveyId="+surveyId;
        } else if (3 == rule) {
            String ruleCode = request.getParameter("ruleCode");
            String surveyRuleCode = surveyDetail.getRuleCode();
            if (ruleCode == null || !ruleCode.equals(surveyRuleCode)) {
//                return ANSWER_INPUT_RULE;
                return "/content/diaowen-answer/response-input-rule";
            }
        }
        return null;
    }
    @RequestMapping("/answerMobile")
    public String answerMobile( HttpServletRequest request,HttpServletResponse response) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
//        HttpServletResponse response = Struts2Utils.getResponse();
        SurveyDirectory directory = directoryManager.getSurvey(surveyId);
        if (directory != null) {
            String filterStatus = filterStatus(directory,request);
            if(filterStatus!=null){
                return filterStatus;
            }
            String htmlPath = directory.getHtmlPath();
            htmlPath = htmlPath.substring(0,htmlPath.lastIndexOf("/"));
            response.setContentType("text/html;charset=utf-8");
            request.getRequestDispatcher("/" + htmlPath+"/m_"+surveyId+".html").forward(request,response);
            return CommonConstant.NONE;
        }
        return CommonConstant.NONE;
    }

    @RequestMapping("/save")
    public String save(String sid,HttpServletRequest request,HttpServletResponse response) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
//        HttpServletResponse response = Struts2Utils.getResponse();
        String formFrom = request.getParameter("form-from");
        try {
            String ipAddr = ipService.getIp(request);
            long ipNum = surveyAnswerManager.getCountByIp(surveyId, ipAddr);
            SurveyDirectory directory = directoryManager.getSurvey(surveyId);

            SurveyDetail surveyDetail = directory.getSurveyDetail();
            int refreshNum = surveyDetail.getRefreshNum();
            User user = accountManager.getCurUser();
            SurveyAnswer entity = new SurveyAnswer();
            if (user != null) {
                entity.setUserId(user.getId());
            }
            Cookie cookie = CookieUtils.getCookie(request, surveyId);
            Integer effectiveIp = surveyDetail.getEffectiveIp();
            Integer effective = surveyDetail.getEffective();
            if ((effective != null && effective > 1 && cookie != null) || (effectiveIp != null && effectiveIp == 1 && ipNum > 0)) {
//                return RELOAD_ANSER_ERROR;
                return "redirect:/dwsurvey/dws-answer/answerError?surveyId="+surveyId;
            }
            if (ipNum >= refreshNum) {
                String code = request.getParameter("jcaptchaInput");
                if (!imageCaptchaService.validateResponseForID(request.getSession().getId(), code)) {
//                    return ANSWER_CODE_ERROR;
                    return "redirect:/dwsurvey/dws-answer/execute/"+sid+".html?errorcode=3";
                }
            }
            Map<String, Map<String, Object>> quMaps = buildSaveSurveyMap(request);
            String addr = ipService.getCountry(ipAddr);
            String city = ipService.getCurCityByCountry(addr);
            entity.setIpAddr(ipAddr);
            entity.setAddr(addr);
            entity.setCity(city);
            entity.setSurveyId(surveyId);
            entity.setDataSource(0);
            surveyAnswerManager.saveAnswer(entity, quMaps);
            int effe = surveyDetail.getEffectiveTime();
            CookieUtils.addCookie(response, surveyId, (ipNum + 1) + "",
                    effe * 60, "/");
        } catch (Exception e) {
            e.printStackTrace();
//            return RELOAD_ANSWER_FAILURE;
            return "redirect:/dwsurvey/dws-answer/answerFailure?surveyId="+surveyId;
        }
        return "redirect:/dwsurvey/dws-answer/answerSuccess?sid="+sid;
//        return RELOAD_ANSWER_SUCCESS;
    }
    @RequestMapping("/saveMobile")
    public String saveMobile(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
//        HttpServletResponse response = Struts2Utils.getResponse();

        try {
            String ipAddr = ipService.getIp(request);
            long ipNum = surveyAnswerManager.getCountByIp(surveyId, ipAddr);
            SurveyDirectory directory = directoryManager.getSurvey(surveyId);
            SurveyDetail surveyDetail = directory.getSurveyDetail();
            int refreshNum = surveyDetail.getRefreshNum();
            User user = accountManager.getCurUser();

            SurveyAnswer entity = new SurveyAnswer();
            if (user != null) {
                entity.setUserId(user.getId());
            }
            Cookie cookie = CookieUtils.getCookie(request, surveyId);
            Integer effectiveIp = surveyDetail.getEffectiveIp();
            Integer effective = surveyDetail.getEffective();
            if ((effective != null && effective > 1 && cookie != null) || (effectiveIp != null && effectiveIp == 1 && ipNum > 0)) {
                return "redirect:/dwsurvey/dws-answer/answerErrorM?surveyId="+surveyId;
//                return RELOAD_ANSER_ERROR_M;
            }
            if (ipNum >= refreshNum) {
                String code = request.getParameter("jcaptchaInput");
                if (!imageCaptchaService.validateResponseForID(request
                        .getSession().getId(), code)) {
                    return "redirect:/survey/answerSurveryMobile?surveyId="+surveyId;
//                    return ANSWER_CODE_ERROR_M;
                }
            }

            Map<String, Map<String, Object>> quMaps = buildSaveSurveyMap(request);
            String addr = ipService.getCountry(ipAddr);
            String city = ipService.getCurCityByCountry(addr);
            entity.setIpAddr(ipAddr);
            entity.setAddr(addr);
            entity.setCity(city);
            entity.setSurveyId(surveyId);
            entity.setDataSource(0);
            surveyAnswerManager.saveAnswer(entity, quMaps);

            int effe = surveyDetail.getEffectiveTime();
            CookieUtils.addCookie(response, surveyId, (ipNum + 1) + "",
                    effe * 60, "/");
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/dwsurvey/dws-answer/answerFailure?surveyId="+surveyId;
//            return RELOAD_ANSWER_FAILURE;
        }
        return "redirect:/dwsurvey/dws-answer/answerSuccessM?surveyId="+surveyId;
//        return RELOAD_ANSWER_SUCCESS_M;
    }


    public Map<String, Map<String, Object>> buildSaveSurveyMap(HttpServletRequest request) {
        Map<String, Map<String, Object>> quMaps = new HashMap<String, Map<String, Object>>();
        Map<String, Object> yesnoMaps = WebUtils.getParametersStartingWith(
                request, "qu_" + QuType.YESNO + "_");
        quMaps.put("yesnoMaps", yesnoMaps);
        Map<String, Object> radioMaps = WebUtils.getParametersStartingWith(
                request, "qu_"+ QuType.RADIO + "_");
        Map<String, Object> checkboxMaps = WebUtils.getParametersStartingWith(
                request, "qu_"+ QuType.CHECKBOX + "_");
        Map<String, Object> fillblankMaps = WebUtils.getParametersStartingWith(
                request, "qu_" + QuType.FILLBLANK + "_");
        quMaps.put("fillblankMaps", fillblankMaps);
        Map<String, Object> dfillblankMaps = WebUtils
                .getParametersStartingWith(request, "qu_"
                        + QuType.MULTIFILLBLANK + "_");
        for (String key : dfillblankMaps.keySet()) {
            String dfillValue = dfillblankMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(
                    request, dfillValue);
            dfillblankMaps.put(key, map);
        }
        quMaps.put("multifillblankMaps", dfillblankMaps);
        Map<String, Object> answerMaps = WebUtils.getParametersStartingWith(
                request, "qu_" + QuType.ANSWER + "_");
        quMaps.put("answerMaps", answerMaps);
        Map<String, Object> compRadioMaps = WebUtils.getParametersStartingWith(
                request, "qu_" + QuType.COMPRADIO + "_");
        for (String key : compRadioMaps.keySet()) {
            String enId = key;
            String quItemId = compRadioMaps.get(key).toString();
            String otherText = request.getParameter("text_qu_"
                    + QuType.COMPRADIO + "_" + enId + "_" + quItemId);
            AnRadio anRadio = new AnRadio();
            anRadio.setQuId(enId);
            anRadio.setQuItemId(quItemId);
            anRadio.setOtherText(otherText);
            compRadioMaps.put(key, anRadio);
        }
        quMaps.put("compRadioMaps", compRadioMaps);
        Map<String, Object> compCheckboxMaps = WebUtils
                .getParametersStartingWith(request, "qu_" + QuType.COMPCHECKBOX
                        + "_");//复合多选
        for (String key : compCheckboxMaps.keySet()) {
            String dfillValue = compCheckboxMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(
                    request, "tag_" + dfillValue);
            for (String key2 : map.keySet()) {
                String quItemId = map.get(key2).toString();
                String otherText = request.getParameter("text_"
                        + dfillValue + quItemId);
                AnCheckbox anCheckbox = new AnCheckbox();
                anCheckbox.setQuItemId(quItemId);
                anCheckbox.setOtherText(otherText);
                map.put(key2, anCheckbox);
            }
            compCheckboxMaps.put(key, map);
        }
        quMaps.put("compCheckboxMaps", compCheckboxMaps);
        Map<String, Object> enumMaps = WebUtils.getParametersStartingWith(request, "qu_" + QuType.ENUMQU + "_");//枚举
        quMaps.put("enumMaps", enumMaps);
        Map<String, Object> quOrderMaps = WebUtils.getParametersStartingWith(
                request, "qu_" + QuType.ORDERQU + "_");//排序
        for (String key : quOrderMaps.keySet()) {
            String tag = quOrderMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(
                    request, tag);
            quOrderMaps.put(key, map);
        }
        quMaps.put("quOrderMaps", quOrderMaps);
        for (String key:radioMaps.keySet()) {
            String enId = key;
            String quItemId = radioMaps.get(key).toString();
            String otherText = request.getParameter("text_qu_"
                    + QuType.RADIO + "_" + enId + "_" + quItemId);
            AnRadio anRadio = new AnRadio();
            anRadio.setQuId(enId);
            anRadio.setQuItemId(quItemId);
            anRadio.setOtherText(otherText);
            radioMaps.put(key, anRadio);
        }
        quMaps.put("compRadioMaps", radioMaps);
        for (String key : checkboxMaps.keySet()) {
            String dfillValue = checkboxMaps.get(key).toString();
            Map<String, Object> map = WebUtils.getParametersStartingWith(
                    request, dfillValue);
            for (String key2 : map.keySet()) {
                String quItemId = map.get(key2).toString();
                String otherText = request.getParameter("text_"
                        + dfillValue + quItemId);
                AnCheckbox anCheckbox = new AnCheckbox();
                anCheckbox.setQuItemId(quItemId);
                anCheckbox.setOtherText(otherText);
                map.put(key2, anCheckbox);
            }
            checkboxMaps.put(key, map);
        }
        quMaps.put("compCheckboxMaps", checkboxMaps);
        return quMaps;
    }
    @RequestMapping("/answerSuccess")
    public String answerSuccess(String sid,HttpServletRequest request) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
        SurveyDirectory directory = directoryManager.getSurveyBySid(sid);
        request.setAttribute("surveyName", directory.getSurveyName());
        request.setAttribute("viewAnswer", directory.getViewAnswer());
        request.setAttribute("sid", directory.getSid());
        return "/content/diaowen-answer/response-success";
//        return ANSWER_SUCCESS;
    }

    @RequestMapping("/answerFailure")
    public String answerFailure(HttpServletRequest request) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
        SurveyDirectory directory = directoryManager.get(surveyId);
        request.setAttribute("surveyName", directory.getSurveyName());
        request.setAttribute("sId", directory.getSid());
        return "/content/diaowen-answer/response-failure";
//        return ANSWER_FAILURE;
    }
    @RequestMapping("/answerError")
    public String answerError(HttpServletRequest request) throws Exception {
        answerErrorC(request);
        return "/content/diaowen-answer/response-input-error";
//        return ANSWER_ERROR;
    }

    private void answerErrorC(HttpServletRequest request) {
//        HttpServletRequest request = Struts2Utils.getRequest();
        SurveyDirectory directory = directoryManager.get(surveyId);
        request.setAttribute("surveyName", directory.getSurveyName());
        request.setAttribute("sId", directory.getSid());
        String ipAddr = ipService.getIp(request);
        request.setAttribute("ip", ipAddr);
    }

    @RequestMapping("/answerErrorM")
    public String answerErrorM(HttpServletRequest request) throws Exception {
        answerErrorC(request);
        return "/content/diaowen-answer/response-input-error-m";
//        return ANSWER_ERROR_M;
    }

    @RequestMapping("/answerSuccessM")
    public String answerSuccessM(HttpServletRequest request) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
        SurveyDirectory directory = directoryManager.get(surveyId);
        request.setAttribute("directory", directory);
//        return ANSWER_SUCCESS_M;
        return "/content/diaowen-answer/response-success-m";
    }

    @RequestMapping("/ajaxCheckSurvey")
    public String ajaxCheckSurvey(HttpServletRequest request,HttpServletResponse response) throws Exception {
//        HttpServletRequest request = Struts2Utils.getRequest();
//        HttpServletResponse response = Struts2Utils.getResponse();
        // 0 1 2
        String ajaxResult = "0";
        try {
            SurveyDirectory directory = directoryManager.getSurvey(surveyId);
            SurveyDetail surveyDetail = directory.getSurveyDetail();
            int effective = surveyDetail.getEffective();
            int rule = surveyDetail.getRule();
            request.setAttribute("directory", directory);
            String surveyStatus = "0";
            Cookie cookie = CookieUtils.getCookie(request, surveyId);
            String ip = ipService.getIp(request);
            Long ipNum = 0L;
            if (effective > 1) {
                if (cookie != null) {
                    String cookieValue = cookie.getValue();
                    if (cookieValue != null
                            && NumberUtils.isNumeric(cookieValue)) {
                        ipNum = Long.parseLong(cookieValue);
                    }
                    surveyStatus = "1";
                } else {
                }
            }

            ipNum = surveyAnswerManager.getCountByIp(surveyId, ip);
            if (ipNum == null) {
                ipNum = 0L;
            }
            Integer effectiveIp = surveyDetail.getEffectiveIp();
            if (effectiveIp != null && effectiveIp == 1 && ipNum > 0) {
                surveyStatus = "2";
            }
            String isCheckCode = "0";
            int refreshNum = surveyDetail.getRefreshNum();
            if (ipNum >= refreshNum) {
                isCheckCode = "3";
            }
            ajaxResult = "{surveyStatus:\"" + surveyStatus
                    + "\",isCheckCode:\"" + isCheckCode + "\"}";
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.getWriter().write(ajaxResult);
        return null;
    }

//    public String getSid() {
//        return sid;
//    }
//
//    public void setSid(String sid) {
//        this.sid = sid;
//    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

}