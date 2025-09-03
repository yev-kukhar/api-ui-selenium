package org.yevhenii.ui_tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("ui-tests")
@Tag("critical")
public class SearchTest extends BaseUiTest {

    static final String GLOBAL_PARAMETER = "global value";
    final String searchQuery = "Accedo";

    @Test
    @DisplayName("Verify all 1st page search results on Google contain keyword")
    void validateAllSearchResultsOnFirstPageTest() {

        List<String> keywords = List.of("accedo");

        googlePage().openGoogle();
        googlePage().searchFor(searchQuery);

        assertTrue(googlePage().isSearchedResultsContainsKeyword(keywords),
                "Search results do not contain the keyword");
    }


//    @Tag("critical")
//    @Epic("User Experience")
//    @Feature("Search")
//    @DisplayName("Verifying search functionality with various keywords")
//
//    @ParameterizedTest(name = "Search for '{0}' and expect '{1}' results")
//    @CsvSource({
//            "Selenide, 10",
//            "Allure Report, 5",
//            "Jenkins, 8",
//            "JUnit 5, 5"
//    })
//    @Story("As a user, I want to search and get relevant results")
//    @Description("This test verifies that a search for a specific keyword returns the expected number of results.")
//    void searchWithKeywordsTest(String keyword, int expectedResults) {
//
//        Allure.step("Performing search for the keyword: " + keyword);
//        // Here you would use Selenide to perform the actual search.
//        // For a demonstration, we'll just simulate it.
//
//        // Example:
//        // open("https://www.google.com");
//        // $("#q").setValue(keyword).pressEnter();
//
//        Allure.step("Waiting for results to load...");
//
//        int actualResults = 0;
//
//        // Simulate the result count.
//        // In a real test, you would get the actual count from the page.
//        // For this demo, let's make some of them match and some not.
//        if (keyword.equals("JUnit 5")) {
//            actualResults = 5; // Simulating a successful match
//        } else {
//            // For the other keywords, let's assume the actual result count is what we expected.
//            actualResults = expectedResults;
//        }
//
//        Allure.step("Verifying that the number of results is as expected.");
//
//        // Use an assertion to verify the result
//        assertEquals(expectedResults, actualResults, "The number of search results does not match the expected count.");
//
//        // Add an attachment to the report for more detail
//        String attachmentContent = "Search completed successfully for keyword '" + keyword + "' with " + actualResults + " results.";
//        Allure.addAttachment("Search Results Log", "text/plain", attachmentContent);
//    }
//
//
//    @DisplayName("To demonstrate Allure nested report steps")
//    @Test
//    public void complexNestedStepsTest() {
//
//        // from local file (json, xml), vault, mvn profile..
//        final String user = "TestUser";
//        final String password = "Password123";
//
//        Allure.step(String.format("Executing a custom script for [%s]", user), (scenarioStep) -> {
//            scenarioStep.parameter("user", user);
//
//            Allure.step("Step: Authorization", (authStep) -> {
//                authStep.parameter("login", user);
//                // Auth logic
//                System.out.println("Attempt to log in...");
//                Allure.step("Checking successful authorization");
//            });
//
//            Allure.step("Step: Creating a new object", (createStep) -> {
//                final String objectName = "NewObject";
//                createStep.parameter("object_name", objectName);
//                // logic for adding an object
//                System.out.println(String.format("Creating an object: %s", objectName));
//                Allure.step("Checking if an object is saved");
//            });
//
//            Allure.step("Step: Deleting an object", (deleteStep) -> {
//                final String objectId = "ID123";
//                deleteStep.parameter("object_id", objectId);
//                // logic for removing an object
//                System.out.println(String.format("Deleting an object с ID: %s", objectId));
//                Allure.step("Checking for removal");
//            });
//        });
//    }
}
