package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import tacos.domain.User;
import tacos.repository.UserRepository;

@Configuration
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService(UserRepository userRepo) {
		return username -> {
			User user = userRepo.findByUsername(username);
			if (user != null)
				return user;

			throw new UsernameNotFoundException("User '" + username + "' not found");
		};
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web
				.ignoring().requestMatchers("/h2-console/**");
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests(
						(authorizeHttpRequests) -> {
							authorizeHttpRequests.requestMatchers("/design", "/orders").hasRole("USER");
							authorizeHttpRequests.requestMatchers("/", "/**", "/h2-console/**").permitAll();
						}
				)
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/design")
//				.and()
//					.oauth2Login()
				.and()
			        .logout()
			          .logoutSuccessUrl("/")
//				.and()
//					.csrf()
//						.ignoringRequestMatchers("/h2-console/**")
//				// Allow pages to be loaded in frames from the same origin; needed for H2-Console
//				.and()
//					.headers()
//						.frameOptions()
//							.sameOrigin()
				.and()
				.build();
	}
}
