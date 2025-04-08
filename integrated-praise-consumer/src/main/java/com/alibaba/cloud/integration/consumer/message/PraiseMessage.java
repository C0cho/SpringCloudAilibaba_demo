package com.alibaba.cloud.integration.consumer.message;

public class PraiseMessage {

    private Integer itemId;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "PraiseMessage{" + "itemId=" + itemId + '}';
    }

}
