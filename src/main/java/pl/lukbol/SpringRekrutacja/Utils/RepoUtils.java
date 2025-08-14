package pl.lukbol.SpringRekrutacja.Utils;

import org.springframework.stereotype.Component;
import pl.lukbol.SpringRekrutacja.DTO.BranchDTO;
import pl.lukbol.SpringRekrutacja.DTO.BranchRawDTO;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryDTO;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryRawDTO;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RepoUtils {
    public static boolean isNotFork(RepositoryRawDTO repo) {
        return !repo.fork();
    }

    public RepositoryDTO mapToRepositoryDTO(RepositoryRawDTO repo, List<BranchRawDTO> branches) {
        return new RepositoryDTO(
                repo.name(),
                repo.owner().login(),
                mapToBranchDTOs(branches)
        );
    }

    private List<BranchDTO> mapToBranchDTOs(List<BranchRawDTO> branches) {
        return branches.stream()
                .map(branch -> new BranchDTO(
                        branch.name(),
                        branch.commit().sha()
                ))
                .toList();
    }
}
