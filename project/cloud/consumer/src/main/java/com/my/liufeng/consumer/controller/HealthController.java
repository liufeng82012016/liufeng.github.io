package com.my.liufeng.consumer.controller;

import com.my.liufeng.consumer.vo.HealthVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Tag(name = "HealthController",description = "服务健康检查")
@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping("/time")
    @Operation(summary = "获取当前服务器信息",description = "")
    @Parameters(value = {
            @Parameter(name = "origin", description = "请求来源", required = false, in = ParameterIn.QUERY)
    })
    @ApiResponses(value = {
            @ApiResponse(description = "服务健康信息")
    })
    public HealthVO time(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ":" + request.getHeader(headerName));
        }
        HealthVO healthVO = new HealthVO();
        healthVO.setTime(System.currentTimeMillis());
        return healthVO;
    }
}
