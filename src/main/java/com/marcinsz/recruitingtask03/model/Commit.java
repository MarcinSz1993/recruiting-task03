package com.marcinsz.recruitingtask03.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Commit {
    @JsonProperty("sha")
    private String lastCommitSha;

}
