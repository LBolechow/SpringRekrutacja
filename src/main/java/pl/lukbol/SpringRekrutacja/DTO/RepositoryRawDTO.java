package pl.lukbol.SpringRekrutacja.DTO;

public record RepositoryRawDTO(
        String name,
        boolean fork,
        OwnerRawDTO owner
) {}
