package pl.lukbol.SpringRekrutacja.DTO;

import java.util.List;

public record RepositoryDTO(String repositoryName, String ownerLogin, List<BranchDTO> branches) {}
