package com.bihell.dice.model.dto;

import com.bihell.dice.model.domain.SnippetFile;
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
