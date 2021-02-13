package com.heroesApi;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@ComponentScan
@EnableCaching
public class Appmain {

	public static void main(String[] args) {
		SpringApplication.run(Appmain.class, args);
	}
	@Bean
	public CacheManager cacheManager() {
	    SimpleCacheManager cacheManager = new SimpleCacheManager();
	    List<Cache> caches = new ArrayList<Cache>();
	    caches.add(new ConcurrentMapCache("getAll"));
	    caches.add(new ConcurrentMapCache("getById"));
	    caches.add(new ConcurrentMapCache("getNameLike"));
	    cacheManager.setCaches(caches);
	    return cacheManager;
	}
}
