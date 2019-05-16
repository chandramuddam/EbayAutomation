Feature: Ebay shopping

  Scenario: Simple ebay shopping scenario
    Given I launch eBay website
    And I enter the text "Samsung air conditioner"
    When I click on the search item
    Then I navigate to the "Item page"
    When I click on "Add to cart" button
    Then The item should be added to the shopping cart
    And I verify the item count "1" in the shopping cart
    And I click on "Close cart window" button
    When I enter the text "LG air conditioner"
    And I click on the search item
    Then I navigate to the "Item page"
    When I click on "Add to cart" button
    Then The item should be added to the shopping cart
    And I verify the item count "2" in the shopping cart
    And I navigate to the "Cart summary page"
    And I clear the cart
