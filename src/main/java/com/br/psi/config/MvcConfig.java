/*
 * package com.br.psi.config;
 * 
 * import org.springframework.context.annotation.Configuration; import
 * org.springframework.web.servlet.config.annotation.EnableWebMvc; import
 * org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 * import
 * org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 * 
 * @EnableWebMvc
 * 
 * @Configuration public class MvcConfig extends WebMvcConfigurerAdapter {
 * 
 * @Override public void addResourceHandlers( ResourceHandlerRegistry registry)
 * {
 * 
 * registry.addResourceHandler("/static/**")
 * .addResourceLocations("/target/classes/static");
 * registry.addResourceHandler("/*.js")
 * .addResourceLocations("/target/classes/static");
 * registry.addResourceHandler("/*.json")
 * .addResourceLocations("/target/classes/static");
 * registry.addResourceHandler("/*.ico")
 * .addResourceLocations("/target/classes/static");
 * registry.addResourceHandler("/index.html")
 * .addResourceLocations("/target/classes/static/index.html"); } }
 */