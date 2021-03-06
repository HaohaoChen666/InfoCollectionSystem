package com.key.dwsurvey.pojo;

import java.io.Serializable;

public class AnCheckbox implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.belong_answer_id
     *
     * @mbggenerated
     */
    private String belongAnswerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.belong_id
     *
     * @mbggenerated
     */
    private String belongId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.other_text
     *
     * @mbggenerated
     */
    private String otherText;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.qu_id
     *
     * @mbggenerated
     */
    private String quId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.qu_item_id
     *
     * @mbggenerated
     */
    private String quItemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_an_checkbox.visibility
     *
     * @mbggenerated
     */
    private Integer visibility;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_an_checkbox
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.id
     *
     * @return the value of t_an_checkbox.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.id
     *
     * @param id the value for t_an_checkbox.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.belong_answer_id
     *
     * @return the value of t_an_checkbox.belong_answer_id
     *
     * @mbggenerated
     */
    public String getBelongAnswerId() {
        return belongAnswerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.belong_answer_id
     *
     * @param belongAnswerId the value for t_an_checkbox.belong_answer_id
     *
     * @mbggenerated
     */
    public void setBelongAnswerId(String belongAnswerId) {
        this.belongAnswerId = belongAnswerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.belong_id
     *
     * @return the value of t_an_checkbox.belong_id
     *
     * @mbggenerated
     */
    public String getBelongId() {
        return belongId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.belong_id
     *
     * @param belongId the value for t_an_checkbox.belong_id
     *
     * @mbggenerated
     */
    public void setBelongId(String belongId) {
        this.belongId = belongId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.other_text
     *
     * @return the value of t_an_checkbox.other_text
     *
     * @mbggenerated
     */
    public String getOtherText() {
        return otherText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.other_text
     *
     * @param otherText the value for t_an_checkbox.other_text
     *
     * @mbggenerated
     */
    public void setOtherText(String otherText) {
        this.otherText = otherText;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.qu_id
     *
     * @return the value of t_an_checkbox.qu_id
     *
     * @mbggenerated
     */
    public String getQuId() {
        return quId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.qu_id
     *
     * @param quId the value for t_an_checkbox.qu_id
     *
     * @mbggenerated
     */
    public void setQuId(String quId) {
        this.quId = quId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.qu_item_id
     *
     * @return the value of t_an_checkbox.qu_item_id
     *
     * @mbggenerated
     */
    public String getQuItemId() {
        return quItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.qu_item_id
     *
     * @param quItemId the value for t_an_checkbox.qu_item_id
     *
     * @mbggenerated
     */
    public void setQuItemId(String quItemId) {
        this.quItemId = quItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_an_checkbox.visibility
     *
     * @return the value of t_an_checkbox.visibility
     *
     * @mbggenerated
     */
    public Integer getVisibility() {
        return visibility;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_an_checkbox.visibility
     *
     * @param visibility the value for t_an_checkbox.visibility
     *
     * @mbggenerated
     */
    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }
}