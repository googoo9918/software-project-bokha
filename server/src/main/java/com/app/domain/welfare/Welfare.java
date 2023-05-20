package com.app.domain.welfare;

import static com.google.common.base.Preconditions.*;
import static org.apache.commons.lang3.StringUtils.*;

import com.google.common.base.Preconditions;
import java.time.LocalDateTime;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

@Getter
public class Welfare {


    private String id;
    private String name; //

    private String ctpvNm; // 시도명

    private String sggNm; // 시군구 명

    private String content; // 서비스 요약


    public Welfare(String id, String name, String ctpvNm, String sggNm, String content) {

        checkArgument(isNotEmpty(id), "id must be provided");
        checkArgument(isNotEmpty(name), "name must be provided");
        checkArgument(isNotEmpty(ctpvNm), "ctpvNm must be provided");
        checkArgument(isNotEmpty(sggNm), "sggNm must be provided");
        checkArgument(isNotEmpty(content), "content must be provided");

        this.id = id;
        this.name = name;
        this.ctpvNm = ctpvNm;
        this.sggNm = sggNm;
        this.content = content;
    }

    public static Welfare Of(Element eElement){
        String serviceName = eElement.getElementsByTagName("servNm").item(0).getTextContent();
        String serviceId = eElement.getElementsByTagName("servId").item(0).getTextContent();
        String ctpvNm = eElement.getElementsByTagName("ctpvNm").item(0).getTextContent();
        String sggNm = eElement.getElementsByTagName("sggNm").item(0).getTextContent();
        String content = eElement.getElementsByTagName("servDgst").item(0).getTextContent();
        return new Welfare(serviceId,serviceName,ctpvNm,sggNm,content);
    }



}
