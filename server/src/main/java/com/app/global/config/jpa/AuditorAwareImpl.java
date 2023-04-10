package com.app.global.config.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
// jpa Audit 기능 설정
    @Autowired
    private HttpServletRequest httpServletRequest;
//    uri 정보를 가져오기 위해 ServletRequest DI

    @Override
    public Optional<String> getCurrentAuditor() {
        String modifiedBy = httpServletRequest.getRequestURI();
//        uri정보를 가져옴
        if(!StringUtils.hasText(modifiedBy)) {
            modifiedBy = "unknown";
//           uri 정보가 빈 값이면 unknown으로 설정
        }
        return Optional.of(modifiedBy);
//        수정자에 해당하는 uri 정보 리턴
    }

}
