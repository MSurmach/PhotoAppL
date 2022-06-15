package com.udemy.users.service;

import com.udemy.users.data.model.AlbumResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("albums-ws")
public interface AlbumsService {
    @GetMapping("/users/{userId}/albums")
    List<AlbumResponseModel> getAlbums(@PathVariable(name = "userId") String userId);
}
