package pl.lukbol.SpringRekrutacja;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import pl.lukbol.SpringRekrutacja.DTO.RepositoryDTO;
import pl.lukbol.SpringRekrutacja.Services.RepositoryService;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test") // Dodajemy profil testowy
class SpringRekrutacjaApplicationTests {

	@Autowired
	private RepositoryService repositoryService;

	private WireMockServer wireMockServer;

	@BeforeAll
	void setupWiremock() {
		wireMockServer = new WireMockServer(8089);
		wireMockServer.start();

		configureFor("localhost", 8089);

		// Stub dla listy repozytoriów
		stubFor(get(urlEqualTo("/users/octocat/repos"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody("""
                            [
                              {"name": "Repo1", "owner": {"login":"octocat"}, "fork": false},
                              {"name": "Repo2", "owner": {"login":"octocat"}, "fork": false}
                            ]
                        """)));

		// Stub dla branchy Repo1
		stubFor(get(urlEqualTo("/repos/octocat/Repo1/branches"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody("""
                            [{"name":"main","commit":{"sha":"abc123"}}]
                        """)));

		// Stub dla branchy Repo2
		stubFor(get(urlEqualTo("/repos/octocat/Repo2/branches"))
				.willReturn(aResponse()
						.withHeader("Content-Type", "application/json")
						.withBody("""
                            [{"name":"develop","commit":{"sha":"def456"}}]
                        """)));
	}

	@AfterAll
	void teardown() {
		wireMockServer.stop();
	}

	@Test
	void shouldFetchRepositoriesWithBranches() {
		List<RepositoryDTO> result = repositoryService.getRepositoryInfo("octocat");

		// Sprawdzenie liczby repozytoriów
		assertThat(result).hasSize(2);

		// Sortowanie wyników dla pewności kolejności
		result.sort((r1, r2) -> r1.repositoryName().compareTo(r2.repositoryName()));

		// Repo1
		RepositoryDTO repo1 = result.get(0);
		assertThat(repo1.repositoryName()).isEqualTo("Repo1");
		assertThat(repo1.branches()).hasSize(1);
		assertThat(repo1.branches().get(0).name()).isEqualTo("main");
		assertThat(repo1.branches().get(0).lastCommitSha()).isEqualTo("abc123");

		// Repo2
		RepositoryDTO repo2 = result.get(1);
		assertThat(repo2.repositoryName()).isEqualTo("Repo2");
		assertThat(repo2.branches()).hasSize(1);
		assertThat(repo2.branches().get(0).name()).isEqualTo("develop");
		assertThat(repo2.branches().get(0).lastCommitSha()).isEqualTo("def456");
	}
}