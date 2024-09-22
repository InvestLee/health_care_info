package com.health_care_info.api.common.error;

public interface ErrorCode {

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
