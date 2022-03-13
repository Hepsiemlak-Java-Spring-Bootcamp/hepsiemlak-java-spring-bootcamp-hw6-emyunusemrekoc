package main.java.emlakburada.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import emlakburada.client.request.BannerRequest;
import emlakburada.client.response.BannerResponse;
@FeignClient(value = "feign",url ="http://localhost:8081" )
public interface BannerClient {

	@PostMapping(value = "/banners")
	BannerResponse saveBanner(BannerRequest bannerRequest);



}
