package com.showmual.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.AllArgsConstructor;

@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
 
	private final UserDetailsService userDetailsService;
 
	@Bean
	public static BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		/* @formatter:off */
		http
			.authorizeRequests()
				.antMatchers("/css/**").permitAll()
				.antMatchers("/js/**").permitAll()
				.antMatchers("/scss/**").permitAll()
				.antMatchers("/img/**").permitAll()
				.antMatchers("/fonts/**").permitAll()
				.antMatchers("/", "/closet", "/closet/", "/closet/index").permitAll() // 메인페이지
				.antMatchers("/closet/findId", "/closet/findId/findId.do").permitAll() // ID 찾기
				.antMatchers("/closet/findPw", "/closet/findPw/findPw.do").permitAll() // PW 찾기
				.antMatchers("/closet/manCoordi", "/closet/womanCoordi").permitAll() // 코디 페이지
				.antMatchers("/closet/searchManCoordi", "/closet/searchWomanCoordi").permitAll() // 코디 검색 기능
				.antMatchers("/closet/signup", "/closet/login", "/closet/analysis", "/closet/test", 
				        "/image/**", "/closet/idCheck", "/closet/nicknameCheck", "/closet/community", 
				        "/closet/notice", "/closet/viewNotice").permitAll() // 설정한 리소스의 접근을 인증절차 없이 허용
				.antMatchers("/closet/admin/**").hasRole("ADMIN") // 관리자 페이지
				.antMatchers("/closet/myinfo").hasRole("MEMBER") // 마이페이지
				.anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
			.and()
	            // csrf 토큰 무시시키기
	            .csrf()
	            .ignoringAntMatchers("/closet/idCheck")
	            .ignoringAntMatchers("/closet/nicknameCheck")
	            .ignoringAntMatchers("/closet/bigCategoryList")
                .ignoringAntMatchers("/closet/smallCategoryList")
                .ignoringAntMatchers("/closet/imagePathList")
                .ignoringAntMatchers("/closet/diaryImagePathList")
                .ignoringAntMatchers("/closet/removeCommunity")
                .ignoringAntMatchers("/closet/searchManCoordi")
                .ignoringAntMatchers("/closet/searchWomanCoordi")
	        .and()
			    .formLogin()
				.permitAll()
				.loginPage("/closet/login") // 기본 로그인 페이지
				.defaultSuccessUrl("/closet") // 로그인 성공 시 갈 페이지
			.and()
			    .logout()
				.permitAll()
				// .logoutUrl("/logout") // 로그아웃 URL (기본 값 : /logout)
				.logoutSuccessUrl("/closet") // 로그아웃 성공 URL (기본 값 : "/login?logout")
				.logoutRequestMatcher(new AntPathRequestMatcher("/closet/logout")) // 주소창에 요청해도 포스트로 인식하여 로그아웃
				.deleteCookies("JSESSIONID") // 로그아웃 시 JSESSIONID 제거
				.invalidateHttpSession(true) // 로그아웃 시 세션 종료
				.clearAuthentication(true) // 로그아웃 시 권한 제거
			.and()
			    .exceptionHandling().accessDeniedPage("/closet/denied");
				
		return http.build();
		/* @formatter:on */
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.httpFirewall(defaultHttpFirewall());
//	}
//	
//	@Bean
//	public HttpFirewall defaultHttpFirewall() {
//		return new DefaultHttpFirewall();
//	}
	
}
