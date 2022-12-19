package com.showmual.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.showmual.entity.imageClassification.ImageClothRepository;

@Service
public class ImageClothService {
	
	private final WebClient webClient;

	public ImageClothService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("http://127.0.0.1:8000").build();
	}
	
	// 분석
	public ImageClothRepository getFirstTodoTest(String imgUrl) {
		
		return this.webClient
				.get()
				.uri(uriBuilder -> uriBuilder
					.path("/noticeboard/response/")
					.queryParam("imgUrl", imgUrl)
		            .build())
				.retrieve()
				.bodyToMono(ImageClothRepository.class)
				.block();
	}
	
	// 등록
	public ImageClothRepository getFirstTodoTest2(String imgUrl) {
	    
	    return this.webClient
	            .get()
	            .uri(uriBuilder -> uriBuilder
	                    .path("/noticeboard/response2/")
	                    .queryParam("imgUrl", imgUrl)
	                    .build())
	            .retrieve()
	            .bodyToMono(ImageClothRepository.class)
	            .block();
	}
	
}
