package org.frankframework.backend;

import io.github.wimdeblauwe.testcontainers.cypress.CypressContainer;
import io.github.wimdeblauwe.testcontainers.cypress.CypressTestResults;
import io.github.wimdeblauwe.testcontainers.cypress.CypressTestSuite;
import jakarta.annotation.Nonnull;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Stream;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Runs e2e tests with Cypress in a Testcontainer.
 * Requires Docker, else the test will be skipped.
 * <p>
 * Exclude with '-DexcludedGroups=integration'
 *
 * @author Sergi Philipsen
 * @see "https://github.com/wimdeblauwe/testcontainers-cypress"
 */
@Log4j2
@Testcontainers(disabledWithoutDocker = true)
@Tag("integration")
public class RunCypressE2eTest {
    private static CypressContainer container;
    private static ConfigurableApplicationContext run;
    private static final Path mochawesomeReportsDir = Paths.get("target/test-classes/e2e/cypress/test-results/reports/mochawesome");

    @BeforeAll
    static void setUp() {
        startBackend();
        startTestContainer();
    }

    private static void startBackend() {
        SpringApplication springApplication = BackendApplication.configureApplication();

        run = springApplication.run();

        assertTrue(run.isRunning());
        await().pollInterval(5, TimeUnit.SECONDS)
                .atMost(Duration.ofMinutes(5))
                .until(() -> verifyAppIsHealthy());
    }

    private static boolean verifyAppIsHealthy() {
        try {
            String url = "http://localhost:8080/actuator/health";
            HttpRequest req = HttpRequest.newBuilder(URI.create(url))
                    .GET()
                    .build();
            HttpResponse<String> resp = HttpClient.newHttpClient()
                    .send(req, HttpResponse.BodyHandlers.ofString());
            return resp.statusCode() == 200 && resp.body().contains("UP");
        } catch (Exception e) {
            return false;
        }
    }


    public static void startTestContainer() {
        org.testcontainers.Testcontainers.exposeHostPorts(8080);

        container = new CypressContainer("cypress/included:15.0.0");

        // It has to be like this and not use the builder
        container.withBaseUrl("http://host.testcontainers.internal:8080");
        container.withMochawesomeReportsAt(mochawesomeReportsDir);

        container.start();
        assertTrue(container.isRunning());
    }

    @AfterAll
    static void tearDown() {
        if (run == null) return;

        run.stop();
        container.stop();

        assertFalse(run.isRunning());
        assertFalse(container.isRunning());

        run.close();
    }

    @TestFactory
    @Nonnull
    Stream<DynamicContainer> runCypressTests() throws InterruptedException, IOException, TimeoutException {
        CypressTestResults testResults = container.getTestResults();

        return testResults.getSuites()
                .stream()
                .map(this::createContainerFromSuite);
    }

    private DynamicContainer createContainerFromSuite(CypressTestSuite suite) {
        Stream<DynamicTest> dynamicTests = suite.getTests().stream()
                .map(test -> DynamicTest.dynamicTest(
                        test.getDescription(), () -> {
                            if (!test.isSuccess()) {
                                assertTrue(verifyAppIsHealthy(), "!! application not reachable !!");
                            }
                            assertTrue(test.isSuccess(), test::getErrorMessage);
                        }
                ));

        return DynamicContainer.dynamicContainer(suite.getTitle(), dynamicTests);
    }
}
