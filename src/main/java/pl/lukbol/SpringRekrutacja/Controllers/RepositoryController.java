package pl.lukbol.SpringRekrutacja.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryDTO;
import pl.lukbol.SpringRekrutacja.Services.RepositoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepositoryController {

    private final RepositoryService repositoryService;

    private static final  String GET_REPOS_URL = "/users/{username}/repositories";

    @GetMapping(GET_REPOS_URL)
    public List<RepositoryDTO> getRepositories(@PathVariable String username)
    {
        return repositoryService.getRepositoryInfo(username);
    }

}
