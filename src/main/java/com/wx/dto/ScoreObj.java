package com.wx.dto;

import net.sf.jsqlparser.expression.DateTimeLiteralExpression;

import java.util.Date;

public class ScoreObj {
    private Integer scoreid;
    private Integer userid;
    private UserObj userObj;
    private Integer scoretype;


    private Integer scorevalue;

    private Date scoretime;

    private Integer scoredate;

    private Integer memo;

    public Integer getScoreid() {
        return scoreid;
    }

    public void setScoreid(Integer scoreid) {
        this.scoreid = scoreid;
    }

    public Integer getScorevalue() {
        return scorevalue;
    }

    public void setScorevalue(Integer scorevalue) {
        this.scorevalue = scorevalue;
    }
    public UserObj getUserObj() {
        return userObj;
    }

    public void setUserObj(UserObj userObj) {
        this.userObj = userObj;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getScoretype() {
        return scoretype;
    }

    public void setScoretype(Integer scoretype) {
        this.scoretype = scoretype;
    }

    public Date getScoretime() {
        return scoretime;
    }

    public void setScoretime(Date scoretime) {
        this.scoretime = scoretime;
    }

    public Integer getScoredate() {
        return scoredate;
    }

    public void setScoredate(Integer scoredate) {
        this.scoredate = scoredate;
    }

    public Integer getMemo() {
        return memo;
    }

    public void setMemo(Integer memo) {
        this.memo = memo;
    }
}