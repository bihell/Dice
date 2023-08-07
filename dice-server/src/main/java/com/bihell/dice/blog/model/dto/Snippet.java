package com.bihell.dice.blog.model.dto;

import com.bihell.dice.blog.model.tool.SnippetFile;
import lombok.Data;

import java.util.List;

/**
 * @author haseochen
 */
@Data
public class Snippet {
    Integer id;

    String title;

    String description;

    String label;

    List<SnippetFile> snippetFiles;
}
