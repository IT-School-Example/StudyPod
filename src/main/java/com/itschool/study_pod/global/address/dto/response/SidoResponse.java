package com.itschool.study_pod.global.address.dto.response;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class SidoResponse {

    private String sidoCd;

    private String sidoNm;
}