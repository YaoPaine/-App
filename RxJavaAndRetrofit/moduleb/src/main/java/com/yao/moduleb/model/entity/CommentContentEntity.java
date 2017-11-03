package com.yao.moduleb.model.entity;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/3 下午6:20
 * @Version:
 */

public class CommentContentEntity {

    /**
     * hasComment : false
     * averageStar : 0
     * totalComments : 0
     * comments : null
     */

    private boolean hasComment;
    private int averageStar;
    private int totalComments;
    private Object comments;

    public boolean isHasComment() {
        return hasComment;
    }

    public void setHasComment(boolean hasComment) {
        this.hasComment = hasComment;
    }

    public int getAverageStar() {
        return averageStar;
    }

    public void setAverageStar(int averageStar) {
        this.averageStar = averageStar;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
    }

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }
}
