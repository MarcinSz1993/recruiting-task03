package com.marcinsz.recruitingtask03.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExpectedResponse {
    private String repositoryName;
    private String ownerLogin;
    private List<Branch> branchList;
}
