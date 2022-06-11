/**
 * 
 */
package com.app.nutritionist.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author BUG6KOR
 *
 */
@Component
public class RequestWrapperFilter extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(RequestWrapperFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("Logging Request  {} : {}", request.getMethod(), request.getRequestURI());

		/*
		 * String plantId = request.getHeader("PlantId"); String userNtId =
		 * request.getHeader("UserNtId");
		 * 
		 * if (!CommonUtils.isNull(plantId) && !CommonUtils.isNull(userNtId)) {
		 * 
		 * log.info("Request Header PlantId and UserNtId  : {} , {}", plantId,
		 * userNtId);
		 * SingletonProperties.getInstance().setPlantId(Integer.parseInt(plantId));
		 * SingletonProperties.getInstance().setUsername(userNtId); }
		 */

		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		String path = request.getRequestURI();
		log.info("Path ----- "+path);
		return (("/app/v1/user/authenticate".equals(path) || ("/app/v1/user/signup").contains(path)) || path.contains("/app/v1/user/sendotp")
				|| path.contains("/app/v1/user/validateotp") || ("/app/v1/user/updateuserpassword").contains(path) );
	}

}
