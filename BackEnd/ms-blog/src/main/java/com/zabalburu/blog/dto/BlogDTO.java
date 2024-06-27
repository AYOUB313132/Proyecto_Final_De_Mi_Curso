package com.zabalburu.blog.dto;

import java.util.List;
import com.zabalburu.blog.model.Blog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
	
	private int numPagina;
	private int totalPaginas;
	private List<Blog> blogs;
}
