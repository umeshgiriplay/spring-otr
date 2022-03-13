package com.umeshgiri.otr.commonpayload;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse {
    private CommonResponseStatus status;
    private String message;
    private Object object;
}
