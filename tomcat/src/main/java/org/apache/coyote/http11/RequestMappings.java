package org.apache.coyote.http11;

import java.util.List;
import java.util.Optional;

public class RequestMappings {

    private final List<RequestMapping> requestMappings;

    public RequestMappings(RequestMapping... requestMappings) {
        this(List.of(requestMappings));
    }

    private RequestMappings(List<RequestMapping> requestMappings) {
        this.requestMappings = requestMappings;
    }

    public Controller findController(String requestUri) {
        return requestMappings.stream()
                .map(requestMapping -> requestMapping.getControllerIfMapped(requestUri))
                .flatMap(Optional::stream)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("매칭되는 컨트롤러가 없습니다."));
    }
}
