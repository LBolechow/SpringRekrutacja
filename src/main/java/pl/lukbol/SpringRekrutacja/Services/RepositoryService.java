package pl.lukbol.SpringRekrutacja.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import pl.lukbol.SpringRekrutacja.DTO.BranchRawDTO;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryDTO;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryRawDTO;
import pl.lukbol.SpringRekrutacja.Exceptions.UserNotFoundException;
import pl.lukbol.SpringRekrutacja.Utils.RepoUtils;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class RepositoryService {

    /*
    private static final String USER_REPOS_URL = "https://api.github.com/users/{username}/repos";
    private static final String REPO_BRANCHES_URL = "https://api.github.com/repos/{owner}/{repo}/branches";

    @Value("${github.api.url}")
    private String githubApiUrl;



    private final RestClient restClient;

    private final RepoUtils repoUtils;

    public List<RepositoryDTO> getRepositoryInfo(String username) {
        List<RepositoryRawDTO> repositories = restClient.get()
                .uri(USER_REPOS_URL, username)
                .retrieve()
                .onStatus(status -> status.value() == 404, (request, response) -> {
                    throw new UserNotFoundException("GitHub user not found");
                })
                .body(new ParameterizedTypeReference<List<RepositoryRawDTO>>() {});

        if (repositories == null) {
            return Collections.emptyList();
        }

        return repositories.stream()
                .filter(repo -> !repo.fork())
                .map(repo -> {
                    List<BranchRawDTO> branchesData = fetchBranches(repo.owner().login(), repo.name());
                    return repoUtils.mapToRepositoryDTO(repo, branchesData);
                })
                .toList();
    }

    private List<RepositoryRawDTO> fetchRepositories(String username) {
        List<RepositoryRawDTO> response = restClient.get()
                .uri(USER_REPOS_URL, username)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return response != null ? response : Collections.emptyList();
    }

    private List<BranchRawDTO> fetchBranches(String owner, String repoName) {
        List<BranchRawDTO> response = restClient.get()
                .uri(REPO_BRANCHES_URL, owner, repoName)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        return response != null ? response : Collections.emptyList();
    }

     */
}




