package com.bong.jpaquerydsl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bong.jpaquerydsl.domain.item.Album;
import com.bong.jpaquerydsl.domain.item.Book;
import com.bong.jpaquerydsl.domain.item.Item;
import com.bong.jpaquerydsl.domain.item.Movie;
import com.bong.jpaquerydsl.service.ItemService;

@Controller
public class ItemController {
 
	@Autowired
	ItemService itemService;

	@PostMapping(value = "/item/books")
	public @ResponseBody void createBook(Book item) {
		itemService.save(item);
	} 
	
	@PostMapping(value = "/item/albums")
	public @ResponseBody void createAlbums(Album item) {
		itemService.save(item);
	} 
	
	@PostMapping(value = "/item/movie")
	public @ResponseBody void createMovie(Movie item) {
		itemService.save(item);
	} 
	
	@GetMapping(value = "/item/detail/{itemId}")
	public @ResponseBody Item getBooks(@PathVariable Long itemId) {
		return itemService.findOne(itemId);
	} 
} 